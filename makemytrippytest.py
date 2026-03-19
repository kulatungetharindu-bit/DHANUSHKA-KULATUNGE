import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from datetime import datetime, timedelta

@pytest.fixture
def browser():
    service = Service(
        executable_path=r"C:\Users\LENOVO\Desktop\Chrome Driver\DHANUSHKA\chromedriver-win64\chromedriver.exe"
    )
    driver = webdriver.Chrome(service=service)
    driver.maximize_window()
    yield driver
    driver.quit()

def test_round_trip_flight_search(browser):
    browser.get("https://www.makemytrip.com/")

    wait = WebDriverWait(browser, 15)

    # Dismiss any popup overlay/banner
    try:
        wait.until(EC.element_to_be_clickable((By.XPATH, "//div[@class='langCardClose']"))).click()
    except Exception:
        pass



    # Click on From input, type code and select first suggestion
    from_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@id='fromCity']")))
    from_input.click()
    from_input.send_keys("HYD")
    wait.until(EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'HYD')]"))).click()

    # Click on To input, type code and select first suggestion
    to_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@id='toCity']")))
    to_input.click()
    to_input.send_keys("MAA")
    wait.until(EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'MAA')]"))).click()

    # Select departure date (tomorrow)
    dep_date = datetime.now() + timedelta(days=1)
    dep_date_str = dep_date.strftime("%a %b %d %Y")
    depart_picker = browser.find_element(By.XPATH, "//label[@for='departure']")
    depart_picker.click()
    wait.until(EC.element_to_be_clickable(
        (By.XPATH, f"//div[@aria-label='{dep_date_str}']"))
    ).click()

    # Select return date (3 days after departure)
    ret_date = datetime.now() + timedelta(days=4)
    ret_date_str = ret_date.strftime("%a %b %d %Y")
    wait.until(EC.element_to_be_clickable(
        (By.XPATH, f"//div[@aria-label='{ret_date_str}']"))
    ).click()

    # Click Search
    search_btn = wait.until(EC.element_to_be_clickable((By.XPATH, "//a[contains(@class,'primaryBtn')]")))
    search_btn.click()

    # Verify that Search results page loads
    wait.until(EC.title_contains("Search"))
    assert "Search" in browser.title