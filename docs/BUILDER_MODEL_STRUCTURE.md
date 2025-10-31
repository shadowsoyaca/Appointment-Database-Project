BUILDER MODEL STRUCTURE
----------------------------------------------------------------------------------------------

- Depending on which model you choose, you may realize that some of the variables in the object class are __OPTIONAL__.
- If your design does not have optional variables, you can create a model like we were taught from our previous classes.
- Otherwise we will need to use the __builder pattern__ as the overloading practice would require a constructor for every variety of optional variables.
- Regardless, I will start by including everything that either method will need for our project:

General Structure Example
------------------------------------------------
public class example{

//variables

//public constructor (so other files can access the constructor)

//getter methods for every variable

//overridded toString method for debugging purposes that will display the variable name and what is assigned to it.


Builder Pattern Example
-----------------------------------------------

Here is an example of a car model that requests the user to give required and optional information:
<br /><br />
public class Car{
<br /><br />
 <ul>
	 //required variables<br />
    <ul>
		private String make;<br />
  	private String model;<br />
		</ul>
		
<br />
  //optional variables<br />
		<ul>
    private String color;<br />
    private String licensePlate;<br />
		</ul>
		<br />
</ul>
		
  //private constructor (only builder can access this)<br />
	<ul> --Builder must be present as a variable, car can be named to whatever you please-- </ul>
  <ul>
		private Car(Builder car){ <br />
			<ul>
				this.make = builder.make;<br />
				this.model = builder.model;<br />
				this.color = builder.color;<br />
				this.licensePlate = builder.licensePlate;<br />
			</ul>
		}<br />
</ul>
//here is where you would place your getter methods (no changes)<br /><br />

//here is where you would place you toString override (no changes either)<br /><br />

//--Builder Inner Class-- <br />
<ul>
	public static class Builder{ //builder is required<br /><br />
	<ul>
		//all variables must be redeclared<br /><br />
		//required <br />
		private String make;<br />
		private String model; <br /><br />
		//optional<br />
		private String color; <br />
		private String licensePlate; <br />
		<br />
		//constructor for required fields<br />
		public Builder(String make, String model){<br />
		<ul>
			this.make = make;<br />
			this.model = model;<br />
		</ul>
		}<br /><br />
		//if you ever had to do a setter (these can be optional) -- exmaple:<br />
		public Builder color(String color){ this.color = color; return this;}<br />
		<br />
		//build method -- sends the car design back to the private car constructor at the beginning.<br />
		public Car build(){<br />
		<ul>return new Car(this);</ul>
		}
	</ul>
	}
</ul>}<br /><br />


<h2>How To Call the Constructor</h2>

<br />
<ul>
	Car car1 = new Car.Builder("Honda", "Fit", "Blue").build(); 
</ul>
<br />

Let me know if you have any questions!


    
