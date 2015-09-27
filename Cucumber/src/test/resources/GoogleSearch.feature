@GoogleSearch

 Feature: Perform a search on google and get first result
 	
 	@SingleSearch
 	Scenario: Perform a search 
 		Given I open Google page
 		When I type Page Object on search box
 		Then the first result of search contains code.google.com
 		

		 
    @MultiSearch
    Scenario Outline: Perform REST queries and compare with queries from HTTP request
      	Given I open Google page
 		When I type <Query> on search box
 		Then the first result of search contains <Result>
 
   Examples: The following queries will be executed
   	| Query      | Result										|
	|Page Object | code.google.com								|
	|Gamesys	 | wikipedia.org/wiki/Gamesys					|
	