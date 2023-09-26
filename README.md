Здравствуйте! Это моя реализация тестового задания Лаборатории ШИФТ на языке Java.
Приложение испольует Spring Boot, Spring Data JPA, H2.
Написаны unit-тесты для некоторых классов их методов.

Инструкция по сброке:

1. git clone https://github.com/rgoryy/shiftlab-alekseev-23-24-test-task.git
2. dir .\shiftlab-alekseev-23-24-test-task\
3. mvn clean
4. mvn package
5. dir target
6. java -jar test-task-0.0.1-SNAPSHOT.jar

Инструкция к использованию api:
1. URL для всех запросов: localhost:8080/api/v1/intervals
2. Представлено всего по одному Get и Post запросу.
   Endpoints:
   * **Get** - запрос минимального интервала. 
   Числа: /min?kind=digits
   Буквы: /min?kind=letters
   
   * **Post** - добавление интервалов.
   Числа: /merge?kind=digits
   Буквы: /merge?kind=letters
3. Примеры тела запроса и ответа JSON:
    * **Запрос:** ?kind=digits: [ [1, 4], [3, 6], [8, 10] ]
    **Ответ:** [1, 6]
    * **Запрос:** ?kind=letters: [ ["a", "f"], ["d", "j"], ["r", "z"] ]
      **Ответ:** [ a, j ]
   * **Запрос:** ?kind=letters: [ [a, f], [d, j], [r, z] ]
     **Ответ:** [ a, j ]
    