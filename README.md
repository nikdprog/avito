Были реализованы базовые запросы тендеров и предложений, продумана структура базы данных, продумана архитектура приложения на основе паттерна MVC без использования компонента VIEW.

Проблемы, возникшие при выполнении задания, повлиявшие на то, почему не хватило времени:
1) Некоторые неточности в формулировке задания;
2) Особенность postgres, которая заключается в том, что он переводит названия полей в нижний регистр, из-за чего было очень трудно понять, почему hibernate строит очень странные запросы.
3) Проблемы с маппингом перечислений в Spring Boot, на решение которых было потрачено значительное время.
4) Проблема парсинга адреса и порта из системной переменной ввиду отсутствия у java spring встроенной поддержки. (Поэтому прописал в application.properties стандартные значения).

ER-диаграмма базы данных:


![image](https://github.com/user-attachments/assets/bae8d157-c740-470d-8d97-c4a597dfa25a)


Жду вашего фидбека!



## Структура проекта
В данном проекте находится типовой пример для сборки приложения в докере из находящящегося в проекте Dockerfile. Пример на Gradle используется исключительно в качестве шаблона, вы можете переписать проект как вам хочется - главное, что бы Dockerfile находился в корне проекта и приложение отвечало по порту 8080. Других требований нет.

## Задание
В папке "задание" размещена задача.

## Сбор и развертывание приложения
Приложение должно отвечать по порту `8080` (жестко задано в настройках деплоя). После деплоя оно будет доступно по адресу: `https://<имя_проекта>-<уникальный_идентификатор_группы_группы>.avito2024.codenrock.com`

Пример: Для кода из репозитория `/avito2024/cnrprod-team-27437/task1` сформируется домен

```
task1-5447.avito2024.codenrock.com
```

**Для удобства домен указывается в логе сборки**

Логи сборки проекта находятся на вкладке **CI/CD** -> **Jobs**.

Ссылка на собранный проект находится на вкладке **Deployments** -> **Environment**. Вы можете сразу открыть URL по кнопке "Open".

## Доступ к сервисам

### Kubernetes
На вашу команду выделен kubernetes namespace. Для подключения к нему используйте утилиту `kubectl` и `*.kube.config` файл, который вам выдадут организаторы.

Состояние namespace, работающие pods и логи приложений можно посмотреть по адресу [https://dashboard.avito2024.codenrock.com/](https://dashboard.avito2024.codenrock.com/). Для открытия дашборда необходимо выбрать авторизацию через Kubeconfig и указать путь до выданного вам `*.kube.config` файла



