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

# Yapılandırmalar
Uygulama Başlatılmadan önce application.properties dosyasından database ayarları yapılmalıdır.


`
spring.mail.username=email-adress
`

`
spring.mail.password=app-password
`

