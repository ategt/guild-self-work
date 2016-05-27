
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculator</title>
    </head>
    <body>
        <h1>Interest Calculator</h1>

        <form method="POST" action="InterestCalculator">
            <label id="annualInterestRateLabel" for="annualInterestRate">
                Please enter the annual interest rate as a percentage:
            </label>
            <input type="text" id="annualInterestRate" name="annualInterestRate" value="0" />
            <br />

            <label id="initialInvestmentLabel" for="initialInvestment">
                Please enter the Initial Investment:
            </label>
            <input type="text" id="initialInvestment" name="initialInvestment" value="0" />
            <br />

            <label id="initialYearsLabel" for="initialYears">
                Please enter the Number of Years To Calculate For:
            </label>
            <input type="text" id="initialYears" name="initialYears" value="0" />
            <br />

            <label id="compoundingsPerYearLabel" for="compoundingsPerYear">
               Compoundings Per Year:
            </label>
            <input type="text" id="compoundingsPerYear" name="compoundingsPerYear" value="0" />
            <br />

            <br />
            <input type="Submit" name="calculateButton" id="calculateButton" value="Factorize!" />
        </form>



    </body>
</html>
