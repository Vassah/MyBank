# Онлайн банк "My Bank"
## Акантьев Александр и Ермаков Василий Б05-120
Банковская система My Bank представляет из себя веб приложение, разрабатываемое на языке Java с и использованием Spring Framework.
Главной целью нашей команды при разработке банковской системы является эффективность кода, масшатабируемость и соответствие важнейшим стандартам разработки коммерчческих приложений.
 
 ## Используемые технологии
 Данный проект разрабатывается в соответствии с архитектурными практиками MVC. Для уменьшения связанности кода будет использоваться внедрение зависимостей (Dependency Injections). В таком масштабном проекте необходимо применять соответсвующие паттерны программирования: способ построения приложения соответствует паттерну facade, для создания новых счётов будет использоваться Abstract Factory, для создания новых аккаунтов - паттерн builder, каждой операции по счётам создаётся экземпляр транзакции, что соответствует паттерну Command. Каждый экземпляр класса пользователь содержит информацию о всех своих счетах и всех своих транзакциях со всех счетов. Все транзакции можно разделить на два вида: MyBank-MyBank и MyBank-OtherBank. Транзакции MyBank-MyBank располагают всей информацией об обоих пользователях. Транзакции MyBank-OtherBank в свою очередь так же делятся на 2 типа: MyBank->OtherBank и OtherBank->MyBank, что отображается в поле SendRecieveStatus.


## Как запустить
Для запуска необходимо клонировать репозиторий весь репозиторий. Далее установить сборщик проектов maven, если он ещё не установлен и из корневой папк проекта выполнить файл start.sh

Чтобы остановить выполнение программы, необходимо выполнить из корневой папки файл stop.sh