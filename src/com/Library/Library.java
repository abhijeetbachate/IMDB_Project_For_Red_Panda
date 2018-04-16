package com.Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.TestBase.TestBase;

public class Library extends TestBase {

	public static Connection dbconnection() {

		try {

			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:IMDB_Movie_Data.sqlite");
			JOptionPane.showMessageDialog(null, "DB Connected Successfully");
			System.out.println("SQLite DB Connected");
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public static void naviagate_to_top_rated_indian_movies() {

		driver.findElement(By.xpath(prop.getProperty("topratedindianmovies"))).click();
		l.info("Navigated to Top Rated Indian Movies Page");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		l.info("Implicit Wait of 20 Seconds is applied in fetchmoviedetails method.");
		
		for (int i = 1; i <= 250; i++) {

			WebElement name_of_themovie = driver.findElement(
					By.xpath(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[" + i + "]/td[2]/a"));

			WebElement year_of_release = driver.findElement(
					By.xpath(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[" + i + "]/td[2]/span"));

			WebElement rating = driver.findElement(
					By.xpath(".//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[" + i + "]/td[3]/strong"));

			System.out.println(i +" " + name_of_themovie.getText()+" " + year_of_release.getText()+" " + rating.getText());
			
			String sql = "INSERT INTO IMDB_Movie_Data(Id, Movie_name, Year_of_Release, Rating) VALUES (?, ?, ?, ?)";

			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, i);
				pst.setString(2, name_of_themovie.getText());
				pst.setString(3, year_of_release.getText());
				pst.setString(4, rating.getText());

				pst.execute();
				System.out.println("Inserted Successfully");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

}
