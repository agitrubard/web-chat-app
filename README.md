# Web Mesajlaşma Uygulaması :speech_balloon:
### Sisteme giriş yapan kişilerle iletişim kurabileceğiniz web mesajlaşma uygulamasıdır.

    - Spring Boot ile birlikte WebSocket API kullanılarak oluşturulan bir mesajlaşma uygulamasıdır.
    - Kullanıcılar sisteme giriş yaptıktan sonra isimleri ile giriş yaparak sohbet başlatabilirler.

Kullanılan Teknolojiler;
- Java
- Spring Boot
- Maven
- Hibernate
- WebSocket
  * Bir sunucu ile bir istemci arasında iki yönlü bir iletişim kanalı kurmayı mümkün kılan bir iletişim protokolüdür.
- STOMP
  * Veri alışverişi formatını ve kurallarını tanımlayan bir Basit Metin Odaklı Mesajlaşma protokolüdür. WebSocket sadece bir iletişim protokolüdür. Yalnızca belirli bir kullanıcıya mesaj gönderebilmek için STOMP’a ihtiyaç duyarız.
- SockJS

Uygulama : [`Web Chat App`](https://agitrubard-chat-app.herokuapp.com/)
