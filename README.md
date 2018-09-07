# todolists
A simple REST server for maintaining multiple todos

## Steps to run the server
### Option 1: Run locally from source
* Checkout the folder
* run `./gradlew bootRun`

### Option 2: Deploy on Servlet Container like Tomcat
* Checkout the source folder
* Generate war file by running `./gradlew war`
* Deploy the war file on Tomcat

### Request URLs

1. Create a __TodoList__: POST on `http://localhost:8080/todos`
```
{
	"listName":"one",
	"items": [
	{
		"itemText": "One item"
	},
	{
		"itemText: "Item Two"
	}]
}
```
2. Get list of __TodoLists__: GET on `http://localhost:8080/todos`
3. Update a __TodoList__: PUT on `http://localhost:8080/todos/1`
```
{
	"listName":"modified list Name",
	"items": [
	{
		"itemText": "Third item"
	}]
}
```

