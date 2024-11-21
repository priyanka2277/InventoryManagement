The UserController class handles all user-related oprations,such as registration ,updating ,fetching and deletion of users.
It acts as the REST controller for user management in the application.
/api/users/
        Register User
URL:/register
Method:POST
        Update a User
URL:/update/{userId}
Method:PUT
        Get User
  URL:/{userId}
  Method:GET
        Get All Users
  URL:/all
  Method:GET
         Delete a User
    URL:/delete/{userId}
    Method:DELETE

    AuthController
The AuthController class handles user authentication for the application.It provides an endpoint for user login,authenticating user credentials and returning a JWT token upon successful authentication.
URL:/login
Method:POST
          CategoryController 
 Base URL
 /categories
 Endpoints
              Create Category
  URL:/
  Method:POST
              Get Category by ID
  URL:/{id}
  Method:GET
               Get All Categories
  URL:/
  Method:GET
                 Update Category
  URL:/{id}
  Method:PUT
  Path Parameter :
  id(Long):The ID of the category to update
                 Delete Category
  URL:/{id}
  Method:DELETE
                ProductController
  Base URL
  /products
                 Create Product
  URL:/
  Method:POST
                 Get Product byID
  URL:/{id}
  Method:GET
  Path Parameter:
     id(Long):The ID of the product to retrieve

               Get All Products
  URL:/
  Method:GET
               Update Product
  URL:/{id}
  Method:PUT
  Path Parameter:
     id(Long):The id of the product to update

               Delete Product
   URL:/{id}
   Method:DELETE
   Path Parameter:
       id(Long):The id of the product to delete

       SupplierController 

       Base URL
       /suppliers

                 Create Supplier
      URL:/
      Method:POST

                Get Suppplier by ID
      URL:/{id}
      Method:GET
      Path Parameter:
          id(Long):The ID of the supplier to retrieve

               Get All Suppliers
      URL:/
      Method:GET

                 Update Supplier
      URL:/{id}
      Method:PUT
      Path Parameter:
           id(Long):The id of the supplier to update

           Delete Supplier
    URL:/{id}
    Method:DELETE
    Path Parameter:
      id(Long):The id of the supplier to delete

              OrderController

              Base URL:/orders

               Create an Order
       URL:/orders
       Method:POST
       Description:Creates a new order in the inventory

               Get Order by ID
       URL:/orders/{id}
       Method:GET
       Description:Retrives details od an order by its ID.

                Get All Orders
       URL:/orders
       Method:GET
       Description:Fetches a list of all orders in the system

                Update an Order
       URL:/orders/{id}
       Method:PUT
       Description:Updates an existing order

                Delete an Order
       URL:/orders/{id}
       Method:DELETE
       Description:Deletes an order by its ID.

       UserServiceImpl:
         It is a service implementation for managing users in the application.It performs the Following tasks:
         User registration with encrypted passwords.
         Role assignment based on provided roles.
         User update and deletion with role-based authorization.
         Data transfer between DTO and Entity using ModelMapper

         CategoryService
         CategoryService provides a set of operations for managing categories in the application,including:
         .Creation of new categories
         .Retrieval of categories by ID or listing all categories.
         Updating category details.
         Deleting categories.


         ProductService

         All endpoints for ProductService assume the base URL:
         Create a product
          EndPoint:POST/api/products
          Description:Creates a new product and saves it to the database.

          Get Product by ID
          Endpoint:GET/api/products/{id}
          Description:Retrives the details of a specific product using its ID.

          Get All Products
          Endpoints:GET/api/products
          Description:Retrieves a list of all products in the database

          Update a Product
          Endpoint:PUT/api/products/{id}
          Description:Updates an existing product by its ID.

          Delete a Product
          Endpoint:DELETE/api/products/{id}
          Description:Deletes a product by its ID.
          

          

          
          


       
       

       
       
       


       
       
       
      


             

               
          
      
       
        

       
       

       
       
     
  
     
  
  
  
  
  
  
 
          
  
        
