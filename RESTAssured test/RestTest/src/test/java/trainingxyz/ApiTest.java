package trainingxyz;


import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

import models.Product;

import static io.restassured.RestAssured.*;

public class ApiTest {
	private String endpoint;

	@Test
	public void getCategories() {

		endpoint = "http://localhost:80/api_testing/category/read.php";

		var response = given().when().get(endpoint).then();
		response.log().body();

	}

	@Test
	public void getProduct() {

		endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		//Checking status code for GET response
		
		 given().queryParam("id", 2).when().get(endpoint).then().assertThat().statusCode(200);
		
	}
	@Test
	public void getProductResponseFields() {

		endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		//Checking status code for GET response
		
		 given().queryParam("id", 2).when().get(endpoint).then().
		 assertThat().statusCode(200).body("id", equalTo("2"));
		
	}

	@Test
	public void createProduct() {
		endpoint = "http://localhost:80/api_testing/product/create.php";

		String payload = """
				{

				"name":"Water Bottle",
				"description":"Blue Water Bootle, Holds 64 ounces",
				"price":12,
				"category_id":3
				}
				""";

		var response = given().body(payload).when().post(endpoint).then();
		response.log().body();  	
	}
	
	@Test
	public void updateProduct() {
		endpoint="http://localhost:80/api_testing/product/update.php";
		String body="""
				{
				"id":19,
				"name":"Water Bottle",
				"description":"Blue Water Bootle, Holds 64 ounces",
				"price": 15,
				"category_id": 3
				}
				""";
		var response=given().body(body).when().put(endpoint).then();
		response.log().body();
		
	}
	@Test
	public void deleteProduct() {
		endpoint="http://localhost:80/api_testing/product/delete.php";
		String body="""
				{
				"id":22
				}
				""";
		var response=given().body(body).when().delete(endpoint).then();
		response.log().body();
		
		
	}
	
	@Test
	public void createSerializedProduct() {
		endpoint="http://localhost:80/api_testing/product/create.php";
		Product product= new Product("Water Bottle", 
				"Blue water bottle", 
				12, 
				3);
		
		var response=given().body(product).when().post(endpoint).then();
		response.log().body();
		
	}
	
	//testing complex response body
	@Test
	public void getComplexResponseBodyProduct() {

		endpoint = "http://localhost:80/api_testing/product/read.php";

		given().when().get(endpoint).then().
			log().body().assertThat().statusCode(200).header("Content-Type", equalTo("application/json; charset=UTF-8")).
				body("records.size()", greaterThan(0)).
						body("records.id", everyItem(notNullValue()));
		

	}
	
	@Test
	public void getHeaders() {

		endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		//Checking status code for GET response
		
		 given().queryParam("id", 2).when().get(endpoint).then().log().headers();
		
	}
	
	//De-serialized response into java object
	@Test
	public void getDeserializedProduct() {

		endpoint = "http://localhost:80/api_testing/product/read_one.php";
		
		
		
		 given().queryParam("id", 2).when().get(endpoint).as(Product.class);
		
	}

}
