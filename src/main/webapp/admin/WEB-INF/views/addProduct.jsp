<%@include file="head.jsp"  %>
</head>
<body>
 From admin folder of webapp
  ${product}
	<main>

        <form class="product-form" action="/admin/add-product"  method="POST" modelAttribute="product" >
            <!--by default encoding type would be  used for only text.for files we use enc type which multer would look for in incoming request-->
            <div class="form-control">
                <label for="title">Title</label>
                <input type="text" name="title" id="title" >
            </div>
            <div class="form-control">
                <label for="image">Image</label>
                <input type="text" name="imageUrl" id="image" ">
                <!--  <input type="file" name="image" id="image" ">-->
            </div>
    
            <div class="form-control">
                <label for="price">Price</label>
                <input type="number" name="price" id="price" step="0.01" >
            </div>
            <div class="form-control">
                <label for="description">Description</label>
                <textarea name="description" id="description" rows="5"></textarea>
            </div>
        
           
            <button class="btn" type="submit">Submit</button>
        </form>
    </main>
	
</body>
</html>