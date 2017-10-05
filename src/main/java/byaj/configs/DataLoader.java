package byaj.configs;


import byaj.models.Post;
import byaj.models.Role;
import byaj.models.User;
import byaj.models.Video;
import byaj.repositories.PostRepository;
import byaj.repositories.RoleRepository;
import byaj.repositories.UserRepository;
import byaj.repositories.VideoRepository;
import byaj.services.UserService;
import org.hibernate.Hibernate;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.*;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data . . .");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("EMPLOYER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role employerRole = roleRepository.findByRole("EMPLOYER");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("bob@bob.com", "bob", "Bob", "Bobberson", true, "bob", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("jim@jim.com", "jim", "Jim", "Jimmerson", true, "jim", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("urek@urek.com", "urek", "Urek", "Mazino", true, "UrekMazino", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("bam@bam.com", "bam", "Viole", "Grace", true, "Bam", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@secure.com", "TowerOfGod", "Viole", "Grace", true, "FloorAdministrator", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole));
        user.setRoles(Arrays.asList(employerRole));
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        user = new User("sam@every.com", "password", "Sam", "Everyman", true, "everyman", "http://res.cloudinary.com/andrewjonesdev/image/upload/c_scale,h_100/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg", "http://res.cloudinary.com/andrewjonesdev/image/upload/profilepic_kos4l4.jpg");
        user.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);


        System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);
        String baseUrl = "https://andrewjonesdev.tumblr.com/";
        initPhantomJS();
        driver.get(baseUrl);
        //driver = addHtmlVid(driver, 6400);
        //driver = addFlash(driver, 6400);


        //WebElement element;
        //WebDriver driverScroll = scrollToBottom(driver, , 10);


        //int nbArticlesBefore = driver.findElements(By.xpath("//div[@class='tumblr_video_container']/iframe")).size();
        //driver.findElement(By.id("load-more-btn")).click();

        // We wait for the ajax call to fire and to load the response into the page
        //Thread.sleep(800);
        driver = scrollToBottom(driver, driver.findElement(By.xpath("//div[@class='tumblr_video_container']/iframe")), 1200);
        //driver = addHtmlVid(driver, 6400);
        //driver = addFlash(driver, 6400);

        //int nbArticlesAfter = driver.findElements(By.xpath("//div[@class='tumblr_video_container']/iframe")).size();
        //System.out.println(String.format("Initial articles : %s Articles after clicking : %s", nbArticlesBefore, nbArticlesAfter));

        java.util.List<WebElement> s = driver.findElements(By.xpath("//div[@class='tumblr_video_container']/iframe"));
        ArrayList<String> sStr = new ArrayList();
        for(WebElement count : s){
            sStr.add(count.getAttribute("src"));
        }
        //driver.executePhantomJS("var page = this; page.clearCookies(); page.clearMemoryCache(); page.close(); return 'DONE';");
        //driver.quit();
        driver.manage().deleteAllCookies();
        shutdownBrowser(driver);
        Runtime r = Runtime.getRuntime();
        r.gc();

        int reverseCount = sStr.size() + 1;
        int count = 0;
        for (String i : sStr) {
            count++;
            reverseCount--;
            String vidPage = i;
            if (vidPage.toLowerCase().contains("andrewjonesdev")) {
                //System.out.println(i.getAttribute("src"));
                System.setProperty("phantomjs.page.settings.userAgent", USER_AGENT);
                baseUrl = vidPage;
                initPhantomJS();
                //driver = addHtmlVid(driver, 6400);
                //driver = addFlash(driver, 6400);
                System.out.println(baseUrl);
                driver.get(baseUrl);
                System.out.println(count);
                System.out.println(reverseCount);
                String pic = driver.findElement(By.xpath("//video")).getAttribute("poster");
                //String pic = driver.findElement(By.cssSelector("video[preload='none']").
                /*String pic = "";
                List<WebElement> picMedia = driver.findElements(By.className("crt-video crt-skin-default"));
                for(WebElement countMedia : picMedia){
                    if(countMedia.getAttribute("poster").contains(".jpg")){
                        pic = countMedia.getAttribute("poster");
                    }
                }*/

                System.out.println(pic);
                String vid = driver.findElement(By.xpath("//source")).getAttribute("src");
                //String[] url = driver.findElement(By.xpath("//source")).getAttribute("src").split("/");
                /*String vid = "";
                List<WebElement> vidMedia = driver.findElements(By.className("crt-video crt-skin-default"));
                for(WebElement countMedia : vidMedia){
                    if(countMedia.getAttribute("src").contains("video_file")){
                        vid = countMedia.getAttribute("src");
                    }
                }*/
                String[] url = vid.split("/");
                vid = "https://vtt.tumblr.com/" + url[url.length - 1] + ".mp4";
                System.out.println("https://vtt.tumblr.com/" + url[url.length - 1] + ".mp4");
                //System.out.println();

                Video video = new Video("Jess Video #" + (reverseCount - 1) + "!",
                        "New!",
                        pic, pic, pic,
                        vid, vid, vid);
                videoRepository.save(video);
                driver.manage().deleteAllCookies();
                shutdownBrowser(driver);
                r = Runtime.getRuntime();
                r.gc();
                //initPhantomJS();
                //driver.quit();
            }
            //shutdownBrowser(driver);
            //r = Runtime.getRuntime();
            //r.gc();
        }


    }

    private static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static DesiredCapabilities desiredCaps;
    private static WebDriver driver;


    public static void initPhantomJS() {
        desiredCaps = new DesiredCapabilities();
        desiredCaps.setJavascriptEnabled(true);
        desiredCaps.setCapability("takesScreenshot", false);
        desiredCaps.setCapability("phantomjs.page.settings.loadImages", false);
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "vendor/phantomjs/bin/phantomjs");
        //desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", USER_AGENT);

        ArrayList<String> cliArgsCap = new ArrayList();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--webdriver-loglevel=ERROR");
        cliArgsCap.add("--load-images=false");

        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        //driver = new ChromeDriver(desiredCaps);
        driver = new PhantomJSDriver(desiredCaps);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    public static void initChrome() {
        desiredCaps = new DesiredCapabilities();
        desiredCaps.setJavascriptEnabled(false);
        desiredCaps.setCapability("takesScreenshot", false);
        desiredCaps.setCapability("phantomjs.page.settings.loadImages", false);
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "vendor/phantomjs/bin/phantomjs");
        //desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX + "User-Agent", USER_AGENT);

        ArrayList<String> cliArgsCap = new ArrayList();
        cliArgsCap.add("--web-security=false");
        cliArgsCap.add("--ssl-protocol=any");
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--webdriver-loglevel=ERROR");
        cliArgsCap.add("--load-images=false");

        desiredCaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
        driver = new ChromeDriver(desiredCaps);
        //driver = new PhantomJSDriver(desiredCaps);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }
    public synchronized WebDriver scrollToBottom(WebDriver driver, WebElement element, int time) throws InterruptedException {
        String oldpage = "";
        String newpage = "";


        do {
            oldpage = driver.getPageSource();
            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, (document.body.scrollHeight))");
            this.wait(time);
            newpage = driver.getPageSource();
        } while (!oldpage.equals(newpage));
        return driver;
    }

    public static void shutdownBrowser(WebDriver driver) {
        for (int count = 0; count < 10; count++) {
            int errorCheck = 0;
            try {
                Thread.sleep(400);
                driver.quit();
            } catch (Exception ex) {
                errorCheck = 1;
            }
            if (errorCheck == 0)
                break;
        }
    }

    public synchronized WebDriver addHtmlVid(WebDriver driver, int time) throws InterruptedException {

        String oldpage = "";
        String newpage = "";


        do {
            oldpage = driver.getPageSource();
            ((JavascriptExecutor) driver)
                    .executeScript("page.onInitialized = function () {"
                            +"  page.evaluate(function () {document.createElement = function (tag) {"
                            + "var elem = create.call(document, tag);"
                            + "} if (tag === \"video\") {"
                            + "  elem.canPlayType = function () { return \"probably\" };"
                            + "} return elem;};});};");
            this.wait(time);
            newpage = driver.getPageSource();
        } while (!oldpage.equals(newpage));
        return driver;
    }



    public synchronized WebDriver addFlash(WebDriver driver, int time) throws InterruptedException {

        String oldpage = "";
        String newpage = "";


        do {
            oldpage = driver.getPageSource();
            ((JavascriptExecutor) driver)
                    .executeScript("page.onInitialized = function () {"
                            +"  page.evaluate(function () {window.navigator = {"
                            + "plugins: { \"Shockwave Flash\": { description: \"Shockwave Flash 11.2 e202\" } },"
                            + "mimeTypes: { \"application/x-shockwave-flash\": { enabledPlugin: true } } };});};");
            this.wait(time);
            newpage = driver.getPageSource();
        } while (!oldpage.equals(newpage));
        return driver;
    }

}
/*
    page.onInitialized = function () {
  page.evaluate(function () {
    var create = document.createElement;
    document.createElement = function (tag) {
      var elem = create.call(document, tag);
      if (tag === "video") {
        elem.canPlayType = function () { return "probably" };
      }
      return elem;
    };
  });
};*/