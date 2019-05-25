import { AppPage } from './app.po';
import { browser, by, element, logging ,  until} from 'protractor';


describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title of application', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MuzixApplication');
  });


  it('should be redirected to/login page',() =>{
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
    browser.driver.sleep(2000);
  });

  it('should be able to register user',() =>{
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('email')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.register-user')).click();
    browser.driver.sleep(2000);
  });



  it('should be able to login user',()=>{
    browser.element(by.id('username')).sendKeys('test123');
    browser.element(by.id('password')).sendKeys('test123');
    browser.element(by.css('.login-click')).click();
    browser.driver.sleep(2000);
  });

  it('should be able to click on India menu item', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-tracks')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-item-india')).click();
    expect(browser.getCurrentUrl()).toContain('/India');
    browser.driver.sleep(500);
  });

  it('should be able to save India track to wishlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to click on spain menu item', () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-tracks')).click();
    browser.driver.sleep(1000);
    browser.element(by.css('.mat-button-item-spain')).click();
    expect(browser.getCurrentUrl()).toContain('/Spain');
    browser.driver.sleep(500);
  });


  it('should be able to save Spain track to wishlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.addbutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to get all data from wishlist', () => {
    browser.driver.manage().window().maximize();
    browser.element(by.css('.mat-button-wishlist')).click();
    browser.driver.sleep(1000);
    expect(browser.getCurrentUrl()).toContain('/WishList');
  });

  it('should be able to delete data from wishlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.deletebutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to open dialogbox to update comment from wishlist', () => {
    browser.driver.manage().window().maximize();
    const tracks = browser.element(by.css('.example-card'));
    browser.element(by.css('.updatebutton')).click();
    browser.driver.sleep(1000);
  });

  it('should be able to save update comment from wishlist', () => {
    browser.driver.manage().window().maximize();
    browser.element(by.css(".mat-input")).sendKeys("new coomments");
    browser.driver.sleep(1000);
    browser.element(by.css('.updateCommentdemo')).click();
    browser.driver.sleep(1000);
  });


  it('should be able to logout',() =>{
    browser.driver.sleep(500);
    browser.element(by.css('.mat-button-logout')).click();
    browser.driver.sleep(500);
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
