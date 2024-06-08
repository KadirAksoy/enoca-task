# Enoca Projesi

Bu projede Kullanıcı, sepetine ürün ekleme,çıkarma,artırma işlemlerini gerçekleştirir.

## Servisler
CustomerService: Bir kullanıcı ve kullanıcıya bir sepet(cart) yaratır.

ProductService: Ürün ekleme,silme,güncelleme,listeleme gibi işlemleri yapar.

CartService: Sepete ürün ekleme,silme sepeti güncelleme ve sepetin içindekilerini silme işlemini yapar.

OrderService: Sipariş verme, kullanıcının tüm siparişlerini listeleme ve sipariş koduna göre (code) siparişi getirme işlemlerini yapar.


# Kullanılan teknolojiler

- Spring Boot
- MySQL
- Swagger
- Docker
- Mapper

- ### Önkoşullar

Projenin çalıştırılması için aşağıdaki yazılımların yüklü olması gerekmektedir:

- Docker
- Maven
- Java
- MySQL


# Yapılandırmalar
Uygulama Başlatılmadan önce application.properties dosyasından database ayarları yapılmalıdır.

Eğer cihazda MySQL var ise " enocadb " adında bir database yaratıp aşağıdaki kodu çalıştırmak ve database ayarlarını yapmak yeterlidir.

```
cd enoca-task
mvn clean install
mvn spring-boot:run
```


Eğer MySQL yok ise gerekli yapıları docker-compose.yml dosyası ile başlatın:

```
docker-compose up -d
```

Databse için gerekli yapılandırmalar:

`
spring.datasource.username=kendi-username

spring.datasource.password=kendi-password
`


# Swagger
http://localhost:8080/swagger-ui/index.html#/



# Postman işlemleri

## Post işlemleri için örnekler

--- CustomerService
- http://localhost:8080/api/customers/create
{
    "name":"enoca",
    "email":"kadir@aksoy.com"
}

--- ProductService
- http://localhost:8080/api/products/create
{
    "name":" televizyon",
    "price": 50000,
    "stock":5
}

--- CartService
- http://localhost:8080/api/cart/add
{
    "cartId":2,
    "productId":13,
    "quantity":5

}

- http://localhost:8080/api/cart/remove
{
    "cartId":,
    "productId":13,
    "quantity": 2
}

-  http://localhost:8080/api/cart/empyt/{cartId}

--- OrderService

- http://localhost:8080/api/orders/placeOrder/{cartId}




