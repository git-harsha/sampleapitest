# sampleapitest

How to run this sample api test:
1. Clone this git repo
2. Run the following Maven command at the root directory of the cloned copy:
	mvn test -Dapi_key=<<YOUR API KEY>>
3. Test report will be at available test-output/emailable-report.html

Suggestions for further enhancement:
1. Test data should be stored in an easily maintainable place like in database (or Excel file)
2. Test should start with a clean/empty application database. It should be populated by test automation. That way tests will be stable and it will help in writing test cases.

Apart from the sample test provided, other kinds of the tests that can be run are:
1. Field level validations like,
	(a) query is a space character
	(b) query is a special character
	(c) long String in query
	(d) page is greater than total pages, and many more such tests
	(e) large number value for page
2. Pagination tests to check if,
	(a) each page has expected number of items
	(b) it can correctly show correct number when page count is high (depends on data type used to store page count)
3. Search Results
	(a) Does the number of search results match with items in backend/db
	(b) Does wild cards search work (depends on if api support wild cards/regex)
	(c) How does it display when backend data has special characters