
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Interest Calculator</title>
    </head>
    <body>
        <div align="center">
            <h1>Interest Calculator</h1>
            <table cellpadding="10">
                <form method="POST" action="InterestCalculator">
                    <tr>
                        <td>
                            <label id="annualInterestRateLabel" for="annualInterestRate">
                                Please enter the annual interest rate as a percentage:
                            </label>
                        </td>
                        <td>
                            <input type="text" id="annualInterestRate" name="annualInterestRate" value="0" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="initialInvestmentLabel" for="initialInvestment">
                                Please enter the Initial Investment:
                            </label>
                        </td>
                        <td>

                            <input type="text" id="initialInvestment" name="initialInvestment" value="0" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="initialYearsLabel" for="initialYears">
                                Please enter the Number of Years To Calculate For:
                            </label>
                        </td>
                        <td>

                            <input type="text" id="initialYears" name="initialYears" value="0" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="compoundingsPerYearLabel" for="compoundingsPerYear">
                                Compoundings Per Year:
                            </label>
                        </td>
                        <td>

                            <input type="text" id="compoundingsPerYear" name="compoundingsPerYear" value="0" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            
                        </td>
                        <td align="center">

                            <input type="Submit" name="calculateButton" id="calculateButton" value="Calculate!" />
                        </td>
                    </tr>
                </form>
            </table>

        </div>
    </body>
</html>
