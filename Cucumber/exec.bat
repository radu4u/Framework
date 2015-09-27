start ff.bat
call mvn test -Dbrowser=chrome -Dcucumber.options="-t @SingleSearch -f html:target/surefire-reports/cucumber/chrome