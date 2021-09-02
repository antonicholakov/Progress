package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TestingFunctionalities extends BaseTest {

    @Test
    @DisplayName("Creating new record in the table")
    protected void addNewForecast() {

        // Declaring all the selectors
        final By addNewRecordButtonSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[1]/button[1]");
        final By inputDateFieldFromCalendarSelector = By.xpath("//span[@class='k-icon k-i-calendar']");
        final By todaySelector = By.xpath("//span[@class='k-nav-today']");
        final By inputTemperatureInCelsiusSelector = By.xpath("//td[@data-col-index='2']/div/span/input[@class='k-input k-formatted-value']");
        final By inputSummarySelector = By.cssSelector(".k-textbox");
        final By updateButtonSelector = By.xpath("//td[@class='k-command-cell   ']/span/button[@class='k-button telerik-blazor k-button-icontext']/span[@class='k-icon k-i-save ']");
        final By summaryCellSelector = By.xpath("//tr[@class='k-master-row  ' and @data-render-row-index='1']/td[@data-col-index='4']");


        //      Clicking on the the Add New Forecast icon
        WebElement addNewRecordButton = driver.findElement(addNewRecordButtonSelector);
        addNewRecordButton.click();

        //      Choosing the current date from calendar
        WebElement inputDateFieldFromCalendar = driver.findElement(inputDateFieldFromCalendarSelector);
        inputDateFieldFromCalendar.click();

        WebElement today = driver.findElement(todaySelector);
        today.click();

        //      Typing a value for degrees in Celsius
        WebElement inputTemperatureInCelsius = driver.findElement(inputTemperatureInCelsiusSelector);
        inputTemperatureInCelsius.clear();
        inputTemperatureInCelsius.sendKeys("55");

        //      Typing a custom message
        WebElement inputSummary = driver.findElement(inputSummarySelector);
        String summaryMessage = "This is a custom record";
        inputSummary.sendKeys(summaryMessage);

        //      Updating the row in the table
        WebElement updateButton = driver.findElement(updateButtonSelector);
        updateButton.click();

        WebElement summaryCell = driver.findElement(summaryCellSelector);


        //      Comparing the message of input with the actual Summary message from the updated record
        Assertions.assertEquals(summaryMessage, summaryCell.getText());
    }

    @Test
    @DisplayName("Editing and Saving")
    protected void edit() {

        //Declaring all the selectors
        final By editButtonSelector = By.xpath("//tr[@data-render-row-index='1']/td[@colspan='1']/span/button[@class='k-button telerik-blazor k-button-icontext k-primary']");
        final By dateFieldSelector = By.xpath("//span/span/input[@class='k-input']");
        final By inputTemperatureInCelsiusSelector = By.xpath("//td[@class=' k-grid-edit-cell ' and @data-col-index='2']/div/span/input");
        final By inputSummarySelector = By.cssSelector(".k-textbox");
        final By updateButtonSelector = By.xpath("//td[@class='k-command-cell   ']/span/button[@class='k-button telerik-blazor k-button-icontext']/span[@class='k-icon k-i-save ']");
        final By summaryCellSelector = By.xpath("//tr[@class='k-master-row  ' and @data-render-row-index='1']/td[@data-col-index='4']");
        final By temperatureCellSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[3]");
        final By dateCellSelector = By.xpath("//tr[@data-render-row-index='1' and @aria-rowindex='2']/td[@colspan='1' and @data-col-index='1']");

        //Clicking Edit button
        WebElement editButton = driver.findElement(editButtonSelector);
        editButton.click();

        //Entering custom date
        WebElement dateField = driver.findElement(dateFieldSelector);
        dateField.clear();
        String dateToEdit = "15102021";
        dateField.sendKeys(dateToEdit);

        //Entering custom temperature in Celsius
        WebElement inputTemperatureInCelsius = driver.findElement(inputTemperatureInCelsiusSelector);
        inputTemperatureInCelsius.click();
        inputTemperatureInCelsius.sendKeys(Keys.BACK_SPACE);
        inputTemperatureInCelsius.sendKeys(Keys.BACK_SPACE);
        inputTemperatureInCelsius.sendKeys(Keys.BACK_SPACE);
        String editTemperature = "55,0";
        inputTemperatureInCelsius.sendKeys(editTemperature);

        //Entering custom summary message for the record
        WebElement inputSummary = driver.findElement(inputSummarySelector);
        inputSummary.clear();
        String editSummary = "This record is edited";
        inputSummary.sendKeys(editSummary);

        //Clicking Update button
        WebElement updateButton = driver.findElement(updateButtonSelector);
        updateButton.click();

        //Checking the editing
        WebElement summaryCell = driver.findElement(summaryCellSelector);
        WebElement temperatureCell = driver.findElement(temperatureCellSelector);
        WebElement dateCell = driver.findElement(dateCellSelector);

        String dateCellMessage = "петък, 15 окт 2021";

        Assertions.assertEquals(editSummary, summaryCell.getText());
        Assertions.assertEquals(editTemperature, temperatureCell.getText());
        Assertions.assertEquals(dateCellMessage, dateCell.getText());

    }

    @Test
    @DisplayName("Testing the Delete function")
    protected void deleteRecord() {
        //Declaring the selectors
        final By totalNumberOfRecordsBeforeSelector = By.xpath("//span[@class='k-pager-info k-label']");
        final By deleteButtonFirstRowSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[6]/span/button[3]");
        final By actualMessageOfRecordsAfterSelector = By.xpath("//span[@class='k-pager-info k-label']");

        //Getting text of the Items before Delete
        WebElement totalNumberOfRecordsBefore = driver.findElement(totalNumberOfRecordsBeforeSelector);
        String actualMessageOfTheRecordsBefore = totalNumberOfRecordsBefore.getText();
        String messageOfTheRecordsBefore = "1 - 20 of 150 items";

        //Clicking Delete button
        WebElement deleteButtonFirstRow = driver.findElement(deleteButtonFirstRowSelector);
        deleteButtonFirstRow.click();

        //Getting text of the Items after Delete
        WebElement totalNumberOfRecordsAfter = driver.findElement(actualMessageOfRecordsAfterSelector);
        String actualMessageOfRecordsAfter = totalNumberOfRecordsAfter.getText();
        String messageOfTheRecordsAfter = "1 - 20 of 149 items";

        //Checking the first state of the table and if the records was deleted
        Assertions.assertEquals(messageOfTheRecordsBefore, actualMessageOfTheRecordsBefore);
        Assertions.assertEquals(messageOfTheRecordsAfter, actualMessageOfRecordsAfter);

    }

    @Test
    @DisplayName("Testing the forward paging functionality")
    protected void forwardPaging() {
        //Declaring selectors
        final By firstIdOfTable = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[1]");
        final By lastIdOfTable = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[20]/td[1]");
        final By secondPageButton = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[5]/div/ul/li[2]/a");
        final By thirdPageButton = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[5]/div/ul/li[3]/a");

        //Getting the actual first Id of the first page and comparing it with what it should be
        WebElement showFirstIdForPageOne = driver.findElement(firstIdOfTable);
        String actualFirstMessageForPageOne = showFirstIdForPageOne.getText();
        String firstIdForPageOne = "1";

        Assertions.assertEquals(firstIdForPageOne, actualFirstMessageForPageOne);

        //Getting the actual last Id of the first page and comparing it with what it should be
        WebElement showLastIdForPageOne = driver.findElement(lastIdOfTable);
        String actualLastIdForPageOne = showLastIdForPageOne.getText();
        String lastIdForPageOne = "20";

        Assertions.assertEquals(lastIdForPageOne, actualLastIdForPageOne);

        //Going to the second page
        WebElement goToSecondPage = driver.findElement(secondPageButton);
        goToSecondPage.click();

        //Explicitly wait for the first Id of the second page and comparing it with what it should be
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement showFirstIdForPageTwo = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstIdOfTable));

        String actualFirstMessageForPageTwo = showFirstIdForPageTwo.getText();
        String firstIdForPageTwo = "21";

        Assertions.assertEquals(firstIdForPageTwo, actualFirstMessageForPageTwo);

        //Getting the last Id of the second page and comparing it with what is should be
        WebElement showLastIdForPageTwo = driver.findElement(lastIdOfTable);
        String actualLastIdForPageTwo = showLastIdForPageTwo.getText();
        String lastIdForPageTwo = "40";

        Assertions.assertEquals(lastIdForPageTwo, actualLastIdForPageTwo);

        //Going to the third page
        WebElement goToThirdPage = driver.findElement(thirdPageButton);
        goToThirdPage.click();

        //Explicitly wait for the first Id of the third page and comparing it with what it should be
        WebElement showFirstIdForPageThree = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstIdOfTable));
        String actualFirstMessageForPageThree = showFirstIdForPageThree.getText();
        String firstIdForPageThree = "41";

        Assertions.assertEquals(firstIdForPageThree, actualFirstMessageForPageThree);

        // Getting the last Id of the third page and comparing it with what is should be
        WebElement showLastIdForPageThree = driver.findElement(lastIdOfTable);
        String actualLastIdForPageThree = showLastIdForPageThree.getText();
        String lastIdForPageThree = "60";

        Assertions.assertEquals(lastIdForPageThree, actualLastIdForPageThree);

    }

    @Test
    @DisplayName("Testing the backward paging functionality")
    protected void backwardPaging() {

        //Declaring all the selectors
        final By goToFinalPageSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[5]/a[4]");
        final By firstIdOfTheFinalPageSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[1]");
        final By lastIfOfTheFinalPageSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[10]/td[1]");
        final By goToPreviousPageSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[5]/a[2]/span");
        final By firstIdOfTable = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[1]");
        final By lastIdOfTable = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[20]/td[1]");

        //Navigate to the final page
        WebElement goToFinalPage = driver.findElement(goToFinalPageSelector);
        goToFinalPage.click();

        //Explicitly wait for the first Id of the final page and comparing with what it should be
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement firstIdOfTheFinalPage = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstIdOfTheFinalPageSelector));

        String actualFirstIdOfTheFinalPage = firstIdOfTheFinalPage.getText();
        String expectedFirstIdOfTheFinalPage = "141";

        Assertions.assertEquals(actualFirstIdOfTheFinalPage, expectedFirstIdOfTheFinalPage);

        //Comparing the last Id of the final page with what it should be
        WebElement lastIdOfTheFinalPage = driver.findElement(lastIfOfTheFinalPageSelector);
        String actualLastIdOfTheFinalPage = lastIdOfTheFinalPage.getText();
        String expectedLastIdOfTheFinalPage = "150";

        Assertions.assertEquals(actualLastIdOfTheFinalPage, expectedLastIdOfTheFinalPage);

        //Navigating to previous page
        WebElement goToPreviousPage = driver.findElement(goToPreviousPageSelector);
        goToPreviousPage.click();

        //Explicitly wait for the first Id of the seventh page and comparing it with what it should be
        WebElement firstIdOfPageSeven = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstIdOfTable));

        String actualFirstIdOfPageSeven = firstIdOfPageSeven.getText();
        String expectedFirstIdOfPageSeven = "121";

        Assertions.assertEquals(actualFirstIdOfPageSeven, expectedFirstIdOfPageSeven);

        //Comparing the last Id of the seventh page with what it should be
        WebElement lastIdOfPageSeven = driver.findElement(lastIdOfTable);
        String actualLastIdOfPageSeven = lastIdOfPageSeven.getText();
        String expectedLastIdOfPageSeven = "140";

        Assertions.assertEquals(actualLastIdOfPageSeven, expectedLastIdOfPageSeven);

        //Navigating to the sixth page
        goToPreviousPage.click();

        //Explicitly wait for the first Id of the sixth page and comparing it with what it should be
        WebElement firstIdOfPageSix = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstIdOfTable));

        String actualFirstIdOfPageSix = firstIdOfPageSix.getText();
        String expectedFirstIdOfPageSix = "101";

        Assertions.assertEquals(actualFirstIdOfPageSix, expectedFirstIdOfPageSix);

        //Comparing the last Id of the sixth page with what it should be
        WebElement lastIdOfPageSix = driver.findElement(lastIdOfTable);
        String actualLastIdOfPageSix = lastIdOfPageSix.getText();
        String expectedLastIdOfPageSix = "120";

        Assertions.assertEquals(actualLastIdOfPageSix, expectedLastIdOfPageSix);

    }

    @Test
    @DisplayName("Testing the functionality of dragging and dropping headers of the columns")
    protected void dragAndDrop() {

        //Declaring all the selectors
        final By firstHeaderSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[1]/span[1]/span");
        final By fifthHeaderSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[5]/span[1]/span/span");
        final By fourthHeaderSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[4]/span[1]/span/span");

        //Initializing and dragging and dropping the specified column headers
        WebElement idHeader = driver.findElement(firstHeaderSelector);
        WebElement summaryHeader = driver.findElement(fifthHeaderSelector);

        Actions action = new Actions(driver);
        action.dragAndDrop(idHeader, summaryHeader).build().perform();

        //Checking the titles of the replaced headers
        WebElement replacedId = driver.findElement(fifthHeaderSelector);
        String titleOfTheFifthHeader = replacedId.getText();
        String expectedTitleOfTheFifthHeader = "Id";

        Assertions.assertEquals(titleOfTheFifthHeader, expectedTitleOfTheFifthHeader);

        WebElement replacedSummary = driver.findElement(fourthHeaderSelector);
        String titleOfTheFourthHeader = replacedSummary.getText();
        String expectedTitleOfTheFourthHeader = "Summary";

        Assertions.assertEquals(titleOfTheFourthHeader, expectedTitleOfTheFourthHeader);

    }
    @Test
    @DisplayName("Testing the sorting functionality")
    protected void sortAscending() {
        //Declaring all the selectors
        final By temperatureCelsiusHeaderSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[3]/span[1]/span[1]");
        final By firstRow = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[3]");
        final By tenthRow = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[10]/td[3]");
        final By twentiethRow = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[20]/td[3]");

        //Sort by ascending
        WebElement temperatureCelsiusHeader = driver.findElement(temperatureCelsiusHeaderSelector);
        temperatureCelsiusHeader.click();

        //Explicitly wait for the first element to be sorted
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement temperatureCelsiusCellOne = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstRow));

        //Getting the value of the first cell
        String firstCell = temperatureCelsiusCellOne
                .getText()
                .replace(",0","");
        int firstInteger = Integer.parseInt(firstCell);

        //Getting the value of the tenth cell
        WebElement temperatureCelsiusCellTen = driver.findElement(tenthRow);
        String tenthCell = temperatureCelsiusCellTen
                .getText()
                .replace(",0","");
        int tenthInteger = Integer.parseInt(tenthCell);

        //Getting the value of the twentieth cell
        WebElement temperatureCelsiusCellTwenty = driver.findElement(twentiethRow);
        String twentiethCell = temperatureCelsiusCellTwenty
                .getText()
                .replace(",0","");
        int twentiethInteger = Integer.parseInt(twentiethCell);

        //Checking if the values of the certain rows are sorted correctly
        Assertions.assertTrue(firstInteger<=tenthInteger && firstInteger<=twentiethInteger && tenthInteger<=twentiethInteger);

    }

    @Test
    @DisplayName("Testing the sorting functionality")
    protected void descendingSort(){

        //Declaring all the selectors
        final By temperatureInFahrenheitHeaderSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[4]/span[1]");
        final By firstTemperatureRecordDescending = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[1]/td[4]");
        final By tenthTemperatureRecordDescending = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[10]/td[4]");
        final By twentiethTemperatureRecordDescending = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[4]/div[2]/div[1]/div/table/tbody/tr[20]/td[4]");

        //Descending sort of the column
        WebElement temperatureInFahrenheit = driver.findElement(temperatureInFahrenheitHeaderSelector);
        temperatureInFahrenheit.click();
        temperatureInFahrenheit.click();

        //Explicitly wait for the first record that has been sorted and converting it to a number
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement firstTemperatureRecord = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstTemperatureRecordDescending));
        String firstTemperatureRecordText = firstTemperatureRecord
                .getText()
                .replace(",",".");
        double firstTemperature = Double.parseDouble(firstTemperatureRecordText);

        //Converting the tenth record to a number
        WebElement tenthTemperatureRecord = driver.findElement(tenthTemperatureRecordDescending);
        String tenthTemperatureRecordText = tenthTemperatureRecord
                .getText()
                .replace(",",".");
        double tenthTemperature = Double.parseDouble(tenthTemperatureRecordText);

        //Converting the twentieth record to a number
        WebElement twentiethTemperatureRecord = driver.findElement(twentiethTemperatureRecordDescending);
        String twentiethTemperatureRecordText = twentiethTemperatureRecord.getText().replace(",",".");
        double twentiethTemperature = Double.parseDouble(twentiethTemperatureRecordText);

        //Checking the descending order
        Assertions.assertTrue(firstTemperature>=tenthTemperature && firstTemperature>=twentiethTemperature && tenthTemperature>=twentiethTemperature);
    }
    @Test
    @DisplayName("Applying filter for the Summary column")
    protected void filterTableBySummary() {

        //Declaring all the selectors
        final By summaryFilterSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[5]/div/span");
        final By filtersOptionSelector = By.xpath("/html/body/div[7]/div/div/div/div[2]/div/div/div[1]/span[1]/span/span[2]/span");
        final By summaryTextInputFieldSelector = By.xpath("/html/body/div[7]/div/div/div/div[2]/div/div/div[1]/input[1]");
        final By summaryFilterButtonSelector = By.xpath("/html/body/div[7]/div/div/div/div[2]/div/div/div[2]/button[2]");
        final By firstSummaryCellSelector = By.cssSelector("body > div.main > div.content.px-4 > div > div > div.col-12.col-lg-9.border-right > div > div.k-grid-container > div.k-grid-content.k-virtual-content > div:nth-child(1) > div > table > tbody > tr > td:nth-child(5)");

        //Clicking on the Summary filter tab
        WebElement summaryFilter = driver.findElement(summaryFilterSelector);
        summaryFilter.click();

        //Explicitly wait for the Summary filter options
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement filtersOption = wait
                .until(ExpectedConditions.elementToBeClickable(filtersOptionSelector));
        filtersOption.click();

        //Getting the filters options in a list and then choosing "Is equal to"
        ((JavascriptExecutor) driver).executeScript("document.getElementsByClassName('k-input')[0].innerHTML = 'Is equal to'");

        //Typing in the Summary input text field of the filter
        String inputSummaryText = "Bracing";
        WebElement summaryTextInputField = driver.findElement(summaryTextInputFieldSelector);
        summaryTextInputField.sendKeys(inputSummaryText);

        //Clicking the filter button
        WebElement summaryFilterButton = driver.findElement(summaryFilterButtonSelector);
        summaryFilterButton.click();

        //Explicitly wait for the filtered table
        WebElement firstSummaryCell = wait
                .until(ExpectedConditions.visibilityOfElementLocated(firstSummaryCellSelector));
        //Checking the first record of the filtered table to be like the text input filter
        String firstSummaryCellText = firstSummaryCell.getText();

        Assertions.assertEquals(inputSummaryText, firstSummaryCellText);

    }

    @Test
    @DisplayName("Applying and removing filter")
    protected void applyAndRemoveFilter() {

        //Declaring all the selectors
        final By idFilterTabSelector = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[3]/div/table/thead/tr/th[1]/div/span");
        final By increaseValueOneSelector = By.xpath("/html/body/div[3]/div/div/div/div[2]/div/div/div[1]/div[1]/span/span/span[1]/span");
        final By idFilterButtonSelector = By.cssSelector("body > div:nth-child(3) > div > div > div > div.k-columnmenu-item-content > div > div > div.k-actions.k-hstack.k-justify-content-stretch > button.k-button.k-primary");
        final By displayedItemsMessage = By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div/div[5]/span");
        final By idClearFilterButtonSelector = By.xpath("/html/body/div[3]/div/div/div/div[2]/div/div/div[2]/button[1]");

        //Clicking the Id filter tab
        WebElement idFilterTab = driver.findElement(idFilterTabSelector);
        idFilterTab.click();

        //Increasing the value of the id
        WebElement increaseValueOne = driver.findElement(increaseValueOneSelector);
        for (int i = 1; i < 149 ; i++) {
            increaseValueOne.click();
        }

        //Applying filter
        WebElement idFilterButton = driver.findElement(idFilterButtonSelector);
        idFilterButton.click();

        //Checking if the filter is applied and how many items it's showing
        WebElement idAppliedFilter = driver.findElement(displayedItemsMessage);
        String idAppliedFilterMessage = idAppliedFilter.getText();
        String expectedAppliedFilterMessage = "1 - 1 of 1 items";

        Assertions.assertEquals(idAppliedFilterMessage,expectedAppliedFilterMessage);

        //Clicking on the filter tab and clearing the filter
        idFilterTab.click();

        WebElement idClearFilterButton = driver.findElement(idClearFilterButtonSelector);
        idClearFilterButton.click();

        //Explicitly wait for the items of the table when filter is cleared and checking the number of the displayed items
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement idClearedFilter = wait
                .until(ExpectedConditions.visibilityOfElementLocated(displayedItemsMessage));

        String idClearedFilterMessage = idClearedFilter.getText();
        String expectedClearedFilterMessage = "1 - 20 of 150 items";

        Assertions.assertEquals(idClearedFilterMessage,expectedClearedFilterMessage);

    }

}
