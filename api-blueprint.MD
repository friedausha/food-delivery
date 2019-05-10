/user

/user/1 => get

/user/new
{
	"username": "aa",
	"phone_number": "213",
	"password":"pass",
	"role":"1"
}

/user/edit
{
	"id" : 4,
	"username": "aa",
	"phone_number": "213",
	"password":"pass",
	"role":"1"
}

/user/4 => delete

/food

/food/1 => get

/food/new

{
	"name" : "lasagn",
	"price" : 8000
} params : 2

/food/edit

{
	"id" :1,
	"name" : "lasagna",
	"price" : 8000
} params userId: 2

/food/1 
params userId:2
