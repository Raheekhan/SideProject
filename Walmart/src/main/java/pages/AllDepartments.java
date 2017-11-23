package pages;

import helper.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllDepartments {

    private WebDriver driver;
    private Waits wait;
    private Actions action;

    public AllDepartments(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new Waits(driver);
        action = new Actions(driver);
    }

    @FindBy(xpath = "//button[@title='All Departments']")
    WebElement Departments;

    @FindBy(xpath = "//button[@id='superDeptId-0']")
    WebElement ElectronicsAndOffice;

    @FindBy(xpath = "//button[@id='superDeptId-1']")
    WebElement MoviesMusicAndBooks;

    @FindBy(xpath = "//button[@id='superDeptId-2']")
    WebElement HomeFurnitureAndAppliances;

    @FindBy(xpath = "//button[@id='superDeptId-3']")
    WebElement HomeImprovementAndPatio;

    @FindBy(xpath = "//button[@id='superDeptId-4']")
    WebElement ClothingShoesAndJewelry;

    @FindBy(xpath = "//button[@id='superDeptId-5']")
    WebElement BabyAndToddler;

    @FindBy(xpath = "//button[@id='superDeptId-6']")
    WebElement ToysAndVideoGames;

    @FindBy(xpath = "//button[@id='superDeptId-7']")
    WebElement FoodHouseholdAndPets;

    @FindBy(xpath = "//button[@id='superDeptId-8']")
    WebElement PharmacyHealthAndBeauty;

    @FindBy(xpath = "//button[@id='superDeptId-9']")
    WebElement SportsFitnessAndOutdoors;

    @FindBy(xpath = "//button[@id='superDeptId-10']")
    WebElement AutoAndTires;

    @FindBy(xpath = "//button[@id='superDeptId-11']")
    WebElement PhotoAndPersonalizedShop;

    @FindBy(xpath = "//button[@id='superDeptId-12']")
    WebElement SewingCraftsAndChristmas;

    @FindBy(xpath = "(//a[text()='See All Departments'])[2]")
    WebElement SelectAllDepartments;

    @FindBy(id = "header-GlobalLefthandNav-dropdown-list-item-0")
    WebElement HolidaySpecial;

    public void hover(WebElement element) {
        action.moveToElement(element).perform();
    }

    public void selectCategory(String department, String category) {

        selectDepartments();
        WebElement element = driver.findElement(By.xpath("//a[text()='" + category + "']"));

        /**
         * All the Departments are in the 'departments.properties'
         * file in the resources directory.
         */

        if (department.contains("Electronic")) {
            getElectronicsAndOffice();
            element.click();
        } else if (department.contains("Movies")) {
            getMoviesMusicAndBooks();
            element.click();
        } else if (department.contains("Furniture")) {
            getHomeFurnitureAndAppliances();
            element.click();
        } else if (department.contains("Improvement")) {
            getHomeImprovementAndPatio();
            element.click();
        } else if (department.contains("Clothing")) {
            getClothingShoesAndJewelry();
            element.click();
        } else if (department.contains("Baby")) {
            getBabyAndToddler();
            element.click();
        } else if (department.contains("Toys")) {
            getToysAndVideoGames();
            element.click();
        } else if (department.contains("Food")) {
            getFoodHouseholdAndPets();
            element.click();
        } else if (department.contains("Pharmacy")) {
            getPharmacyHealthAndBeauty();
            element.click();
        } else if (department.contains("Sports")) {
            getSportsFitnessAndOutdoors();
            element.click();
        } else if (department.contains("Auto")) {
            getAutoAndTires();
            element.click();
        } else if (department.contains("Photo")) {
            getPhotoAndPersonalizedShop();
            element.click();
        } else if (department.contains("Sewing")) {
            getSewingCraftsAndChristmas();
            element.click();
        } else if (department.contains("All")) {
            getSelectAllDepartments();
            element.click();
        } else {
            System.out.println("Please provide valid Department, refer to Properties file.");
        }
    }

    public void selectDepartments() {
        wait.waitUntilClickable(Departments, 5);
        hover(Departments);
    }

    public void getHolidaySpecials() {
        selectDepartments();
        wait.waitUntilClickable(HolidaySpecial, 5);
        HolidaySpecial.click();
    }

    public void getElectronicsAndOffice() {
        wait.waitUntilClickable(ElectronicsAndOffice, 5);
        hover(ElectronicsAndOffice);
    }

    public void getMoviesMusicAndBooks() {
        wait.waitUntilClickable(MoviesMusicAndBooks, 5);
        hover(MoviesMusicAndBooks);
    }

    public void getHomeFurnitureAndAppliances() {
        wait.waitUntilClickable(HomeFurnitureAndAppliances, 5);
        hover(HomeFurnitureAndAppliances);
    }

    public void getHomeImprovementAndPatio() {
        wait.waitUntilClickable(HomeImprovementAndPatio, 5);
        hover(HomeImprovementAndPatio);
    }

    public void getClothingShoesAndJewelry() {
        wait.waitUntilClickable(ClothingShoesAndJewelry, 5);
        hover(ClothingShoesAndJewelry);
    }

    public void getBabyAndToddler() {
        wait.waitUntilClickable(BabyAndToddler, 5);
        hover(BabyAndToddler);
    }

    public void getToysAndVideoGames() {
        wait.waitUntilClickable(ToysAndVideoGames, 5);
        hover(ToysAndVideoGames);
    }

    public void getFoodHouseholdAndPets() {
        wait.waitUntilClickable(FoodHouseholdAndPets, 5);
        hover(FoodHouseholdAndPets);
    }

    public void getPharmacyHealthAndBeauty() {
        wait.waitUntilClickable(PharmacyHealthAndBeauty, 5);
        hover(PharmacyHealthAndBeauty);
    }

    public void getSportsFitnessAndOutdoors() {
        wait.waitUntilClickable(SportsFitnessAndOutdoors, 5);
        hover(SportsFitnessAndOutdoors);
    }

    public void getAutoAndTires() {
        wait.waitUntilClickable(AutoAndTires, 5);
        hover(AutoAndTires);
    }

    public void getPhotoAndPersonalizedShop() {
        wait.waitUntilClickable(PhotoAndPersonalizedShop, 5);
        hover(PhotoAndPersonalizedShop);
    }

    public void getSewingCraftsAndChristmas() {
        wait.waitUntilClickable(SewingCraftsAndChristmas, 5);
        hover(SewingCraftsAndChristmas);
    }

    public void getSelectAllDepartments() {
        wait.waitUntilClickable(SelectAllDepartments, 5);
        hover(SelectAllDepartments);
    }
}