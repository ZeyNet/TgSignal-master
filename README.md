За основу были взяты исходники: https://github.com/Ashesh3/TelegramMinecraft

Используйте Java 11+

Описание:
Плагин отправляет в бот телеграм сообще когда игрок наступает на растяжку(Именно растяжку натянутую на крюках)

Команды:
```
/tgsreload : перезапуск конфига. Не испльзуйте если будете менять токен могут быть ошибки.
```

Права:
```
permissions:
  tr.*:
    default: op
    description: Выдаёт все права
    children:
      tr.reload: true
  tr.reload:
    default: op
    description: Права на команду /reload
```
Установка:
- Переместите TgSignal.jar в папку plugin
- Перезапустите сервер
- Перейдите в папку плагина TgSignal и откройте файл config.yml
- Вставьте свой токен и измените параметр enable на true
- Получите свой id из бота @getidsbot
- Перезагрузите сервер

- Как создать бота в телеграм:
- Зайдите в бота @BotFather в телеграме
- Пропишите /newbot и выберите имя и логин(@логин)
- Пропишите /token и вставьте в config.yml в строку bot_token: "сюда" (пример как выглядит токен : 327112310:AAFl8ZigTo4OpiLo-kKmnNFqLCztQHPm1zM)
