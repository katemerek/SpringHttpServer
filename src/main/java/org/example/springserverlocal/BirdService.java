package org.example.springserverlocal;
import com.querydsl.core.types.Predicate;


import java.awt.print.Pageable;
import java.util.List;

public interface BirdService {

        /**
         * Создает нового клиента
         * - @param bird клиент для создания
         */
        void create(Bird bird);

        /**
         * Возвращает список всех имеющихся клиентов
         * @return список птичек
         */

        //ищем по возрасту, включая границы

////        List<Bird> readAll();
//        List<Bird> findAll(Predicate predicate, Pageable pageable);

        /**
         * Возвращает птичку по еe ID
         * @param id - ID птички
         * @return - объект птички с заданным ID
         */
        Bird read(int id);

        /**
         * Обновляет клиента с заданным ID,
         * в соответствии с переданным клиентом
         * @param bird - клиент в соответсвии с которым нужно обновить данные
         * @param id - id клиента которого нужно обновить
         * @return - true если данные были обновлены, иначе false
         */
        boolean update(Bird bird, int id);

        /**
         * Удаляет клиента с заданным ID
         * @param id - id клиента, которого нужно удалить
         * @return - true если клиент был удален, иначе false
         */
        boolean delete(int id);
    }

