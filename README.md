# ОТЧЕТ  
# по курсовой работе  

**Тема:** Реализация фреймворка автоматизированного тестирования веб-приложения Demoblaze  

**Исполнитель:**  
Башинский Данила  
Группа: МИВ241  

---

## Содержание  
1. [Требования к автоматизированному тестированию](#1-требования-к-автоматизированному-тестированию)  
2. [Реализация фреймворка](#2-реализация-фреймворка)  
3. [Тестовые сценарии](#3-тестовые-сценарии)  
4. [Результаты тестирования](#4-результаты-тестирования)  

---

### 1. Требования к автоматизированному тестированию  

#### 1.1 Общие требования  
- Поддержка тестирования веб-приложения [Demoblaze](https://www.demoblaze.com/).  
- Реализация фреймворка с использованием паттерна **Page Object**.  
- Интеграция с **TestNG** для управления тестами.  

#### 1.2 Функциональные требования  
- Автоматизация сценариев:  
  - Проверка категорий товаров.  
  - Тестирование авторизации.  
  - Работа с корзиной покупок.  

#### 1.3 Требования к надежности  
- Обработка исключений (таймауты, алерты).  
- Повторное использование компонентов.  

#### 1.4 Требования к установке  
- Настройка через **Maven**.  
- Использование **ChromeDriver**.  

---

### 2. Реализация фреймворка  

#### 2.1 Архитектура проекта  
![Structure](https://github.com/user-attachments/assets/6926ad04-557d-46de-aae2-eecafc1695f3)


#### 2.2 Используемые технологии  
- **Selenium WebDriver 4.16.1**  
- **TestNG 7.10.1**  
- **Maven**  
- **SLF4J 1.7.36**  

#### 2.3 Паттерн Page Object  
- **BasePage** содержит общую логику.  
- Дочерние классы инкапсулируют методы взаимодействия.  
- Пример для `LoginPage`:  
  ```java
  public class LoginPage extends BasePage {
      @FindBy(id = "loginusername") private WebElement usernameField;
      public void performLogin(String username, String password) { ... }
  }

### 3. Тестовые сценарии

#### 1.1 Проверка категорий товаров
  **Цель**: Убедиться, что категория "Laptops" содержит только ноутбуки.
  
  **Шаги:**
  - Открыть главную страницу.
  - Перейти в раздел "Laptops".
  - Проверить категории товаров.

  Код:  
  ```java
    @Test
    public void verifyLaptopsCategoryProducts() {
        homePage.selectLaptopsCategory();
        List<String> categories = homePage.getProductCategories();
        Assert.assertTrue(categories.stream().allMatch(cat -> cat.equals("notebook")));
    }
  ```

#### 3.2 Тестирование авторизации пользователя  
  **Цель**: Проверить успешный вход в систему.
  
  **Шаги:**
  - Открыть форму авторизации.
  - Ввести логин и пароль.
  - Проверить приветственное сообщение.

  Код:  
  ```java
    @Test
    public void verifySuccessfulUserLogin() {
        homePage.openLoginForm();
        loginPage.performLogin("1234", "1234");
        Assert.assertTrue(homePage.getWelcomeMessage().contains("Welcome 1234"));
    }
  ``` 

#### 3.3 Тестирование корзины покупок
  **Цель**: Проверить удаление товара из корзины.
  
  **Шаги:**
  - Добавить товар в корзину.
  - Перейти в корзину.
  - Удалить товар.

  Код:  
  ```java
    @Test
    public void verifyProductRemovalFromCart() {
        homePage.openFirstProduct();
        productPage.addProductToCart();
        cartPage.openCart();
        cartPage.removeProductFromCart();
        Assert.assertTrue(cartPage.isCartEmpty());
    }
  ```   
---

### 4. Результаты тестирования

  #### **Категории товаров:** 
    ✅ Все товары соответствуют типу "notebook".
  #### **Авторизация:** 
    ✅ Приветственное сообщение отображается корректно.
  #### **Корзина:** 
    ✅ Товар успешно удаляется.
  #### **Логи выполнения:** 
  ```
  [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
  ```
![Result](https://github.com/user-attachments/assets/b6fc96ab-2a3f-479b-9e2d-6115921879e7)
---
