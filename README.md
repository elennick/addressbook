# Address Book

## How to Run

### From IntelliJ IDEA

* Right click on the `AddressbookApplication.java` file and choose `Run main()`
* Once the app starts up, browse to http://localhost:8080

### From CLI

* Using Maven wrapper `./mvnw spring-boot:run` or using locally installed Maven `mvn spring-boot:run`
* Once the app starts up, browse to http://localhost:8080

## General Notes

This project is an implementation of the PagerDuty interview assignment located here -> [PagerDuty_Software_Demo_Discussion_and_Interview_Guide.pdf](PagerDuty_Software_Demo_Discussion_and_Interview_Guide.pdf). This project was created by [Evan Lennick](https://www.evanlennick.com). Here are some assorted notes and thoughts about this implementation:

* The shades of <span style="color:#048a24">GREEN</span>. in the UI were pulled from the PagerDuty brand guidelines here -> https://brandguides.brandfolder.com/pagerduty/color
* I would normally separate the app entry point, controller, service and data access layers into entirely separate modules but I just used packages to modularize to keep things simple.
* There are two Postman collections - one for the PagerDuty Users API and one for this applications API. They can be found here and imported into Postman if you want to see examples of all the relevant APIs in use:
    - [PagerDuty Users API](postman-collections/PagerDuty%20Platform%20API.postman_collection.json)
    - [Addressbook Client API Collection](postman-collections/Addressbook%20Client%20API.postman_collection.json)
* The UI here is driven by <b>Spring MVC + Thymeleaf</b> templating. This is not something I would normally do as it doesn't support the way modern distributed API platforms are built. I did also build an API interface that can be accessed at http://localhost:8080/api/users but this API is not used by the UI. In a more realistic + scaled scenario you'd likely have one or more separate apps with their own middleware that would call these  APIs.
* I didn't have time to set this up but I strongly prefer having service-wide component tests that mock out external dependencies (in this case the PagerDuty API) and then validate the entire integrated service from the top. Spring supports this nicely with <b>@SpringBootTest</b>.
* I am logging PII which is fine since this is all sample data and a test app but in a real situation I would be very careful about ever logging phone numbers, names, emails, etc and would instead try to limit logs to arbitrary platform identifiers.
* There are a lot of unchecked scenarios and most of this just works happy path. In most Spring apps like this I would spend more time creating error handling using <b>@ControllerAdvice</b> and creating a lot of tests to validate the scenarios that might throw errors.
* There is currently no authorization of any kind on the raw APIs at `/api/users`. This is something that most real APIs would require and I would potentially use Spring Security to implement that here or would check authorization somewhere further up the chain in an API Gateway.
* There are some `TODO` comments and `UnsupportedOperationException` exceptions in the project. I intentionally left these things to give an idea of where I know improvements are needed or where I would assume the API might expand. If I was actively working on a branch or a set of changes I would use these to indicate where I am still working or intend to work/cleanup. I try not to leave these things in for final PRs and merges into `main`.

## TODO
This section is just to give an idea of further refactoring and improvements I would make were I to continue this project. It's definitely not exhaustive:

* Add pagination to API.
* Add pagination to UI.
* Consolidate serialization in UserResource Assembler. There is a lot of repeated or similar code here and it's also possible all this logic should be in the service layer instead of with the httpclient.
* Add component tests using the Spring integration test framework.
* Add error handling / ControllerAdvice.
* Consolidate styling into one CSS file instead of having it spread across two files.
* Consolidate Thymeleaf header/footer so that both UI pages can just reuse them.
* Add more logs.
* Add better error responses in API. Right now you just get blank or generic Spring errors.
* Add generic error page. Right now if something breaks you just get the generic Spring 500 page.
