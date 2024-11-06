**KULLANICI KAYDETME SERVISI**

***Kullanıcı kaydetme servisine ihtiyacım var.***

* Client sistem, kullanıcı-adı, İsim, Soyisim göndererek kullanıcıyı kaydedecektir.
    - Kullanıcı adı, daha önce kaydedilmişse isim, soyisim güncellenmelidir.
    - Kullanıcı adı, daha önce kaydedilmemişse, yeni kayıt oluşturulmaldır.
    - Kullanıcı-adı, detayları daha sonra paylaşılacak olan bir servis üzerinden doğrulanacaktır. doğrulama başarısız olursa, Hata verilmelidir.

* Kullanıcı-adı, detayları aşağıda verilen rest-service üzerinden doğrulanmalıdır.
    - REQ:
    ```
    HTTP-POST
    url: http://user-validation.turkcell-validator.com/validate-user
    body: {username: “a-user-name“}
    ```
    - RES:
    ```
    body: {“isValid”:boolean}
    ```