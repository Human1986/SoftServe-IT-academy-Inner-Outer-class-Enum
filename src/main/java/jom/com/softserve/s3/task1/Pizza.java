package jom.com.softserve.s3.task1;

class Pizza {
	private String cheese;
	private String meat;
	private String seafood;
	private String vegetable;
	private String mushroom;

	private Pizza(String cheese) {
		this.cheese = cheese;
	}

	private Pizza(String cheese, String seafood) {
		this.cheese = cheese;
		this.seafood = seafood;
	}

	private Pizza(String meat, String vegetable, String mushroom) {
		this.meat = meat;
		this.vegetable = vegetable;
		this.mushroom = mushroom;
	}

	private Pizza() {

	}

	public static PizzaBuilder base() {
		return new PizzaBuilder();
	}

	public String getCheese() {
		return cheese;
	}

	public String getMeat() {
		return meat;
	}

	public String getSeafood() {
		return seafood;
	}

	public String getVegetable() {
		return vegetable;
	}

	public String getMushroom() {
		return mushroom;
	}

	public static class PizzaBuilder extends Pizza {

		private PizzaBuilder() {
		}

		PizzaBuilder(String cheese) {
			super(cheese);
		}

		PizzaBuilder addCheese(String cheese) {
			return new PizzaBuilder(cheese);
		}

		PizzaBuilder addMeat(String meat) {
			return new PizzaBuilder(meat);
		}

		PizzaBuilder addSeafood(String seafood) {
			return new PizzaBuilder(seafood);
		}

		PizzaBuilder addVegetable(String vegetable) {
			return new PizzaBuilder(vegetable);
		}

		PizzaBuilder addMushroom(String mushroom) {
			return new PizzaBuilder(mushroom);
		}

		public Pizza build() {
			return new Pizza("Cheese", "Seafood", "Mushroom");
		}

	}
}


class Oven {
	public static Pizza cook() {

		return Pizza.base().build();
	}
}