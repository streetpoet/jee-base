package impl.tbmarket;

import interfaces.system.ISystemSingleton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.spstudio.love.system.qualifier.LoveLogged;

@Model
@Default
public class AutoSendMap {

	@EJB ISystemSingleton systemSingletonBean;
	@Inject @LoveLogged Logger log;

	private String mapFolderPath;

	@PostConstruct
	private void construct() {
		List<String> listConfigValue = systemSingletonBean
				.retrieveSystemConfiguration().get("MAP_FOLDER");
		mapFolderPath = listConfigValue.get(0);
		log.info("map folder : " + mapFolderPath);
	}

	public void execute(String from, String to, int requireId) {
		WebDriver d = new FirefoxDriver();
		d.get("http://map.baidu.com/");

		/*
		 * window maximum
		 */
		Dimension screenDims = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenDims.getWidth();
		int height = (int) screenDims.getHeight();
		JavascriptExecutor js = (JavascriptExecutor) d;
		String script = "window.resizeTo(" + width + ", " + height + ");";
		js.executeScript(script);
		System.out.println(script);

		/*
		 * find tab3
		 */
		String ui = "//li[@id='tab3']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();

		/*
		 * find 'DriveSearchSta'
		 */
		ui = "//input[@id='DriveSearchSta']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();
		d.findElement(By.xpath(ui)).sendKeys(from);

		/*
		 * find 'DriveSearchEnd'
		 */
		ui = "//input[@id='DriveSearchEnd']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();
		d.findElement(By.xpath(ui)).sendKeys(to);

		/*
		 * find 'driveSearchBtn'
		 */
		ui = "//input[@id='driveSearchBtn']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();

		/*
		 * find 'tool_tarffic'
		 */
		ui = "//div[@id='tool_tarffic']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();
		sleep(10);

		/*
		 * find 'tool_fullScr'
		 */
		ui = "//div[@id='tool_fullScr']";
		waitForUI(d, ui);
		d.findElement(By.xpath(ui)).click();

		/*
		 * screen shot
		 */
		File file = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
		try {
			// delete old file
			File oldFiles = new File(mapFolderPath + File.separator + requireId);
			if (!oldFiles.exists()){
				oldFiles.mkdirs();
			}
			for (File f: oldFiles.listFiles()){
					f.delete();
			}
			FileUtils.copyFile(file, new File(mapFolderPath + File.separator + requireId + File.separator + file.getName()));
		} catch (Exception e) {
		}
		if (file.exists()) {
			file.delete();
		}

		d.close();
		d.quit();
	}

	private void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (Exception e) {
		}
	}

	private void waitForUI(WebDriver d, final String xpath) {
		(new WebDriverWait(d, 3000)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement we = null;
				try {
					we = d.findElement(By.xpath(xpath));
				} catch (Exception e) {
					System.err.println("finding element: " + xpath);
				}
				return we != null;
			}
		});
	}

}
