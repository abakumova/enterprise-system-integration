<template>
<div class="header">
    <div>
    <button v-if = "authResult" @click="Logout" class="center">Logout</button>
    <div class="name" v-if="checkRoles('ADMIN')">
      <div class="container">
          <table>
          <tr>
            <th>ID</th>
            <th>Code</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
          </tr>
          <tr class="item" v-for="product in products" :key="product.id">
            <!-- <td>{{ product.code}} </td> -->
            <td><input name="id" type="text" id="id" required v-model="product.id"></td>
            <td><input name="code" type="text" id="code" required v-model="product.code"></td>
            <td><input name="name" type="text" id="name" required v-model="product.name"></td>
            <td><input name="price" type="number" id="price" required v-model="product.price"></td>
            <td><input name="description" type="text" id="description" required v-model="product.description"></td>
            <td><button class="update"  @click="updateProduct(product.id, product)">update</button> </td> 
            <td><button class="delete"  @click="deleteProduct(product.id)">delete</button> </td> 
          </tr>
          </table>
    </div>
    <h3>Add a Product</h3>
        <div class="container">
                <td><input name="id" type="text" id="id" placeholder="id" required v-model="Aproduct.id"></td>
                <td><input name="code" type="text" id="code" placeholder="Code" required v-model="Aproduct.code"></td>
                <td><input name="name" type="text" id="name" placeholder="Name" required v-model="Aproduct.name"></td>
                <td><input name="price" type="number" id="price" placeholder="Price" required v-model="Aproduct.price"></td>
                <td><input name="description" type="text" id="description" placeholder="Description" required v-model="Aproduct.description"></td>
        </div>
        <button class="add"  @click="addProduct(Aproduct)"> Add  </button>
    </div>
</div>


  <div class="name" v-if="checkRoles('USER')">
  <div class="container">
          <table>
          <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Cost</th>
            <th>Description</th>
          </tr>
          <tr class="item" v-for="product in products" :key="product.id">
            <td>{{ product.code}} </td>
            <td>{{ product.name }} </td>
            <td> {{product.price }}</td>
            <td>{{ product.description }} </td>
          </tr>
          </table>
    </div>
  </div>
  </div>
</template>

<script>
import auth from "../auth";

export default {
  name: "HomeView",
  components: {
  },
  data: function() {
    return {
    authResult: auth.authenticated(),
    AdminViewContent:'',
    UserViewContent:'',
    products: [],
    Aproduct: {
        id: "",
        code: "",
        name: "",
        price: 0,
        description: "",
      },
      token:  localStorage.getItem('jwtToken')
  }
  },
  methods: {
  Logout() {
            auth.logout();
            location.assign("/");
    },

  checkRoles: function(roles) {
      return auth.hasARoleOf(roles);
    },

    addProduct(product) {
      fetch(`http://localhost:8080/api/products`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${this.token}` 
        },
        body: JSON.stringify(product),
        })
        .then(() => {
          location.assign("/");
        })
        .catch((e) => {
        console.log(e);
        });
    },
    updateProduct(id, product) {
      fetch(`http://localhost:8080/api/products/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${this.token}` 
        },
        body: JSON.stringify( {"id": id, "code": product.code, "name": product.name, "price": product.price, "description": product.description}),
            })
        .then(() => {
          location.assign("/");
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteProduct(id) {
      fetch(`http://localhost:8080/api/products/${id}`, {
        method: "DELETE",
        headers: {"Content-Type": "application/json",
        Authorization: `Bearer ${this.token}` 
        },
      })
        .then(() => {
          location.assign("/");
        }) 
        .catch((e) => {
          console.log(e);
        });
    }, 
  },  
  mounted() {
  //Note that both roles fetch the same data, yet we keep them separate since the sources might not be the same in your project
  if (this.checkRoles('ADMIN')){
    const token = localStorage.getItem('jwtToken');
      // Set the Authorization header with the token value
      const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}` 
      };
      // fetch is a GET request by default unless stated otherwise. Therefore, it will fetch all products from the database
      fetch(`http://localhost:8080/api/products`, { headers })
        .then((response) => response.json())
        .then((data) => (this.products = data))
        .catch((err) => console.log(err.message));
    }

  if (this.checkRoles('USER')){
    const token = localStorage.getItem('jwtToken');
      // Set the Authorization header with the token value
      const headers = {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}` 
      };
      // fetch is a GET request by default unless stated otherwise. Therefore, it will fetch all products from the database
      fetch(`http://localhost:8080/api/products`, { headers })
        .then((response) => response.json())
        .then((data) => (this.products = data))
        .catch((err) => console.log(err.message));
      }
    }
}
</script>
<style scoped>
h1 {
  font-size: 20px;
}
.container {
  background: #d5d7d8;
  box-shadow: 1px 2px 3px rgba(0, 0, 0, 0.2);
  margin-bottom: 30px;
  margin-top: 30px;
  padding: 10px 20px;
  margin: auto;
  width: 80%;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  
}
input{
  width: 100px;
  text-align: center
}
.delete{
    background: red;
}
.update{
    background: blue;
}
.add{
  background: rgb(8, 110, 110);
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  align-items: center;
  text-align: center;
}
</style>