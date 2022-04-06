# ДЗ - 5: Application Events and Listeners
О программе:
На вход программе пользователь вводит любой текст.
Затем программа форматирует текст определенным образом и выводит полученный тект в консоль и параллельно записывает этот тект в файл.

@EventListener
handleFormattingEvent - ловит событие о том, что текст отформатирован(FormattingEvent) и запускает запись этого текста в файл "result.txt".

@EventListener
handleWritingEvent - ловит событие "о записи текста"(WritingEvent). Выводит полученный текст посимвольно в консоль с небольшой задержкой(это нужно, чтобы показать ассинхронность работы
handleFormattingEvent).

 @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
 handleTransactionalIsSuccessfully - ловит событие FileEvent и обрабативает его при условии, что запись прошла без ошибок.
 
 @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
 handleTransactionalIsNotSuccessfully - обрабатывает событие FileEvent, если при записи в файл произлошла ошибка.
