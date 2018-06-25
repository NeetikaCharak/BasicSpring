Fetch - Eager
GET 
select user0_.USER_ID as USER_ID1_2_0_, user0_.USER_NAME as USER_NAM2_2_0_, setaddress1_.My_USER_ID as My_USER_1_1_1_, setaddress1_.CITY as CITY2_1_1_, 
setaddress1_.COUNTRY as COUNTRY3_1_1_, setaddress1_.STATE as STATE4_1_1_, setaddress1_.STREET as STREET5_1_1_, setaddress1_.ADD_ID as ADD_ID6_1_ 
from USER_DETAIL user0_ 
left outer join USER_ADDRESS setaddress1_ 
on user0_.USER_ID=setaddress1_.My_USER_ID where user0_.USER_ID=?

LOAD 
select user0_.USER_ID as USER_ID1_2_0_, user0_.USER_NAME as USER_NAM2_2_0_, setaddress1_.My_USER_ID as My_USER_1_1_1_, setaddress1_.CITY as CITY2_1_1_, 
setaddress1_.COUNTRY as COUNTRY3_1_1_, setaddress1_.STATE as STATE4_1_1_, setaddress1_.STREET as STREET5_1_1_, setaddress1_.ADD_ID as ADD_ID6_1_ 
from USER_DETAIL user0_ 
left outer join USER_ADDRESS setaddress1_ 
on user0_.USER_ID=setaddress1_.My_USER_ID where user0_.USER_ID=?
-----------------------------------------------------------------------------------------------------------------------------------------------------------------
Fetch - Lazy
GET
select user0_.USER_ID as USER_ID1_2_0_, user0_.USER_NAME as USER_NAM2_2_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
When address used
Hibernate: select setaddress0_.My_USER_ID as My_USER_1_1_0_, setaddress0_.CITY as CITY2_1_0_, setaddress0_.COUNTRY as COUNTRY3_1_0_, setaddress0_.STATE as STATE4_1_0_, 
setaddress0_.STREET as STREET5_1_0_, setaddress0_.ADD_ID as ADD_ID6_0_ 
from USER_ADDRESS setaddress0_ where setaddress0_.My_USER_ID=?

LOAD
Hibernate: select user0_.USER_ID as USER_ID1_2_0_, user0_.USER_NAME as USER_NAM2_2_0_ from USER_DETAIL user0_ where user0_.USER_ID=?
When address used
Hibernate: select setaddress0_.My_USER_ID as My_USER_1_1_0_, setaddress0_.CITY as CITY2_1_0_, setaddress0_.COUNTRY as COUNTRY3_1_0_, setaddress0_.STATE as STATE4_1_0_, 
setaddress0_.STREET as STREET5_1_0_, setaddress0_.ADD_ID as ADD_ID6_0_ 
from USER_ADDRESS setaddress0_ where setaddress0_.My_USER_ID=?




