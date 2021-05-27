insert into tour_types (type_id, type_name) values ('1', 'Семейный') ON CONFLICT DO NOTHING;
insert into tour_types (type_id, type_name) values ('2', 'Горнолыжный') ON CONFLICT DO NOTHING;
insert into tour_types (type_id, type_name) values ('3', 'Пляжный') ON CONFLICT DO NOTHING;
insert into tour_types (type_id, type_name) values ('4', 'Экскурсионный') ON CONFLICT DO NOTHING;
insert into tour_types (type_id, type_name) values ('5', 'Экономичный') ON CONFLICT DO NOTHING;
insert into tour_types (type_id, type_name) values ('6', 'Семейный') ON CONFLICT DO NOTHING;

insert into feeding (feeding_id, feeding_name) values ('1', 'без питания') ON CONFLICT DO NOTHING;
insert into feeding (feeding_id, feeding_name) values ('2', 'завтраки') ON CONFLICT DO NOTHING;
insert into feeding (feeding_id, feeding_name) values ('3', 'завтрак + ужин') ON CONFLICT DO NOTHING;
insert into feeding (feeding_id, feeding_name) values ('4', 'завтрак, обед, ужин') ON CONFLICT DO NOTHING;
insert into feeding (feeding_id, feeding_name) values ('5', 'всё включено') ON CONFLICT DO NOTHING;

insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('1', '1-местный') ON CONFLICT DO NOTHING;
insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('2', '2-местный с двумя раздельными кроватями') ON CONFLICT DO NOTHING;
insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('3', '2-местный c одной большой двуспальной кроватью') ON CONFLICT DO NOTHING;
insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('4', '3-местный') ON CONFLICT DO NOTHING;
insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('5', 'маленький ребёнок + 1 взрослый') ON CONFLICT DO NOTHING;
insert into accommodation_types (accommodation_type_id, accommodation_type_name) values ('6', 'маленький ребёнок + 2 взрослых') ON CONFLICT DO NOTHING;

insert into countries (country_id, country_name) values ('1', 'Россия') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('2', 'Франция') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('3', 'Италия') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('4', 'Испания') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('5', 'Греция') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('6', 'Египет') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('7', 'Болгария') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('8', 'Черногория') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('9', 'Германия') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('10', 'Норвегия') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('11', 'Чехия') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('12', 'Швейцария') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('13', 'Беларусь') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('14', 'Япония') ON CONFLICT DO NOTHING;
insert into countries (country_id, country_name) values ('15', 'Корея') ON CONFLICT DO NOTHING;

insert into cities (city_id, city_name, country_id) values ('1', 'Москва', '1') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('2', 'Курск', '1') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('3', 'Санкт-Петербург', '1') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('4', 'Калинград', '1') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('5', 'Париж', '2') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('6', 'Рим', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('7', 'Пиза', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('8', 'Флоренция', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('9', 'Верона', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('10', 'Милан', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('11', 'Венеция', '3') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('12', 'Шамони', '2') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('13', 'Хургада', '6') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('14', 'Минск', '13') ON CONFLICT DO NOTHING;
insert into cities (city_id, city_name, country_id) values ('15', 'Берлин', '9') ON CONFLICT DO NOTHING;

insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('1', '13.04.2021 23:00:00', '14.04.2021 01:00:00', '2000', '1', '4', 'Победа') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('2', '16.04.2021 21:00:00', '17.04.2021 01:00:00', '3000', '4', '1', 'Победа') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('3', '01.04.2021 09:00:00', '01.04.2021 12:30:00', '13000', '1', '6', 'Аэрофлот') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('4', '10.04.2021 16:00:00', '10.04.2021 18:00:00', '19000', '9', '15', 'Аэрофлот') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('5', '13.04.2021 23:00:00', '14.04.2021 02:00:00', '35000', '15', '1', 'Аэрофлот') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('6', '23.04.2021 23:00:00', '25.04.2021 03:00:00', '7000', '1', '13', 'Аэрофлот') ON CONFLICT DO NOTHING;
insert into flights(flight_id, departure_time, arrival_time, price, departure_city_id, arrival_city_id, company) values ('7', '30.04.2021 08:00:00', '30.04.2021 12:00:00', '8000', '13', '1', 'Победа') ON CONFLICT DO NOTHING;

insert into hotels(hotel_id, name, number_of_stars, price_for_one_night, accommodation_type_id, city_id, feeding_id) values ('1', 'A1 Tiba Rose', '4', '1300', '6', '13', '5') ON CONFLICT DO NOTHING;
insert into hotels(hotel_id, name, number_of_stars, price_for_one_night, accommodation_type_id, city_id, feeding_id) values ('2', 'Rome 5*', '5', '10000', '2', '6', '2') ON CONFLICT DO NOTHING;
insert into hotels(hotel_id, name, number_of_stars, price_for_one_night, accommodation_type_id, city_id, feeding_id) values ('3', 'Verona 4*', '4', '6000', '2', '9', '3') ON CONFLICT DO NOTHING;
insert into hotels(hotel_id, name, number_of_stars, price_for_one_night, accommodation_type_id, city_id, feeding_id) values ('4', 'Berlin 5*', '5', '8000', '2', '15', '3') ON CONFLICT DO NOTHING;
insert into hotels(hotel_id, name, number_of_stars, price_for_one_night, accommodation_type_id, city_id, feeding_id) values ('5', 'Kaliningrad 3*', '3', '1400', '1', '4', '1') ON CONFLICT DO NOTHING;

insert into tours(tour_id, begin_date, description, end_date, name, price_for_one_person, tour_type_id) values ('1', '23.04.2021', 'Отличный семейный курорт', '30.05.2021', 'Египет', '25000', '3') ON CONFLICT DO NOTHING;
insert into tours(tour_id, begin_date, description, end_date, name, price_for_one_person, tour_type_id) values ('2', '01.04.2021', 'Тур по городам Италии и Германии', '14.04.2021', 'Европа на лодони', '100000', '4') ON CONFLICT DO NOTHING;
insert into tours(tour_id, begin_date, description, end_date, name, price_for_one_person, tour_type_id) values ('3', '14.04.2021', 'Экономный отдых в Калининграде', '17.04.2021', 'Калининград', '20000', '5') ON CONFLICT DO NOTHING;

insert into tours_flights (tour_tour_id, flights_flight_id) values ('1', '6') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('1', '7') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('2', '3') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('2', '4') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('2', '5') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('3', '1') ON CONFLICT DO NOTHING;
insert into tours_flights (tour_tour_id, flights_flight_id) values ('3', '2') ON CONFLICT DO NOTHING;

insert into tours_cities (tour_tour_id, cities_city_id) values ('1', '3') ON CONFLICT DO NOTHING;
insert into tours_cities (tour_tour_id, cities_city_id) values ('2', '6') ON CONFLICT DO NOTHING;
insert into tours_cities (tour_tour_id, cities_city_id) values ('2', '7') ON CONFLICT DO NOTHING;
insert into tours_cities (tour_tour_id, cities_city_id) values ('2', '9') ON CONFLICT DO NOTHING;
insert into tours_cities (tour_tour_id, cities_city_id) values ('2', '15') ON CONFLICT DO NOTHING;
insert into tours_cities (tour_tour_id, cities_city_id) values ('3', '4') ON CONFLICT DO NOTHING;

insert into tours_hotels (tour_tour_id, hotels_hotel_id) values ('1', '1') ON CONFLICT DO NOTHING;
insert into tours_hotels (tour_tour_id, hotels_hotel_id) values ('2', '2') ON CONFLICT DO NOTHING;
insert into tours_hotels (tour_tour_id, hotels_hotel_id) values ('2', '3') ON CONFLICT DO NOTHING;
insert into tours_hotels (tour_tour_id, hotels_hotel_id) values ('2', '4') ON CONFLICT DO NOTHING;
insert into tours_hotels (tour_tour_id, hotels_hotel_id) values ('3', '5') ON CONFLICT DO NOTHING;

insert into employees (employee_id, email, name, patronymic, phone_number, surname, gender, birthdate, phone_number2, pass, pos) values ('1', 'tanya@ya.ru', 'Татьяна', 'Алексеевна', '+7905503914', 'Волкова', 'Ж', '2002-01-11', null, '1234567890', 'admin') ON CONFLICT DO NOTHING;
insert into employees (employee_id, email, name, patronymic, phone_number, surname, gender, birthdate, phone_number2, pass, pos) values ('2', 'alkuz@ya.ru', 'Алена', 'Алексеевна', '+79038909876', 'Кузнецова', 'Ж', '2000-06-02', null, '1234512345', 'menedz') ON CONFLICT DO NOTHING;
insert into employees (employee_id, email, name, patronymic, phone_number, surname, gender, birthdate, phone_number2, pass, pos) values ('3', 'kkuz@ya.ru', 'Ксения', 'Алексеевна', '+7909876523', 'Кузнецова', 'Ж', '2003-12-31', null, '1234512345', 'operator') ON CONFLICT DO NOTHING;



