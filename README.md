# MathLibrary
This library let you to carry out complex algorithms\
Works in every platform : Linux, Windows, MacOSX\
## IDE Environment
Used Eclipse.
You can also use Netbeans or IntellijIDEA
## Sintax
The sintax for using the library is Simple.
You have Matrix, Vector classes for creating math structures\
Then you have solvers like Regression or Equation solvers\
Finally you can draw the solutions in a window\
### Example
Code
```Java
double[][]values= {{1,1,1,-1},{1,1,-1,1},{1,-1,1,1},{-1,1,1,1}}; //Matrix DataSet
double []outputsX= {49,69,89,99,109,20}; //XValues for regression
double []outputsY= {124,95,36,45,55,30}; //YValues for regression

MathViewer viewer= new MathViewer(600, 600); //Create a window to draw functions
	
double solutions[]=Regression.executePolynomialRegression(outputsX, outputsY, 4); //Execute polynomial regression with grade 3 (4-1)
viewer.draw().addDataset(Dataset.arrayToDataset(outputsX, outputsY)); //Simple draw dataset points
viewer.draw().addFunction(solutions); //Draw the regression solution function
```
Output
![imagen1](https://user-images.githubusercontent.com/18512841/46537073-aa31f480-c8b0-11e8-92f5-e17c42e654e4.png)
