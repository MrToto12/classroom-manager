--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16rc1

-- Started on 2023-11-28 10:48:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4833 (class 1262 OID 16397)
-- Name: DB_Facultad; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "DB_Facultad" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE "DB_Facultad" OWNER TO postgres;

\connect "DB_Facultad"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 24741)
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA public;


--
-- TOC entry 4834 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


--
-- TOC entry 865 (class 1247 OID 24682)
-- Name: dia_de_cursado_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.dia_de_cursado_enum AS ENUM (
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
);


ALTER TYPE public.dia_de_cursado_enum OWNER TO postgres;

--
-- TOC entry 228 (class 1255 OID 24748)
-- Name: remove_accents_before_insert(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_accents_before_insert() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.nombre := unaccent(NEW.nombre);
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.remove_accents_before_insert() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 24586)
-- Name: alumnos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alumnos (
    id integer NOT NULL,
    nombre character varying(75) NOT NULL,
    apellido character varying(75) NOT NULL,
    dni integer NOT NULL,
    fecha_nacimiento date NOT NULL,
    edad integer NOT NULL,
    legajo character varying(20) NOT NULL
);


ALTER TABLE public.alumnos OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24585)
-- Name: alumnos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alumnos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.alumnos_id_seq OWNER TO postgres;

--
-- TOC entry 4835 (class 0 OID 0)
-- Dependencies: 216
-- Name: alumnos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alumnos_id_seq OWNED BY public.alumnos.id;


--
-- TOC entry 221 (class 1259 OID 24602)
-- Name: cursos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cursos (
    id integer NOT NULL,
    tipo_cursado character varying(15) NOT NULL,
    nombre character varying(75) NOT NULL,
    codigo_de_catedra integer NOT NULL,
    descripcion character varying(500) NOT NULL,
    objetivo character varying(250) NOT NULL,
    personas_dirigidas character varying(250) NOT NULL,
    costo double precision NOT NULL,
    hora_inicio character varying NOT NULL,
    hora_cierre character varying NOT NULL,
    dia_de_cursado public.dia_de_cursado_enum NOT NULL,
    id_docente integer,
    link_meet character varying(70) DEFAULT ''::character varying
);


ALTER TABLE public.cursos OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24659)
-- Name: cursos_alumnos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cursos_alumnos (
    id_curso_alumno integer NOT NULL,
    id_curso integer,
    id_alumno integer
);


ALTER TABLE public.cursos_alumnos OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24658)
-- Name: cursos_alumnos_id_curso_alumno_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cursos_alumnos_id_curso_alumno_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cursos_alumnos_id_curso_alumno_seq OWNER TO postgres;

--
-- TOC entry 4836 (class 0 OID 0)
-- Dependencies: 222
-- Name: cursos_alumnos_id_curso_alumno_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cursos_alumnos_id_curso_alumno_seq OWNED BY public.cursos_alumnos.id_curso_alumno;


--
-- TOC entry 220 (class 1259 OID 24601)
-- Name: cursos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cursos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cursos_id_seq OWNER TO postgres;

--
-- TOC entry 4837 (class 0 OID 0)
-- Dependencies: 220
-- Name: cursos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cursos_id_seq OWNED BY public.cursos.id;


--
-- TOC entry 219 (class 1259 OID 24593)
-- Name: docentes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.docentes (
    id integer NOT NULL,
    nombre character varying(75) NOT NULL,
    apellido character varying(75) NOT NULL,
    dni integer NOT NULL,
    fecha_nacimiento date NOT NULL,
    edad integer NOT NULL,
    cv character varying(1500) NOT NULL
);


ALTER TABLE public.docentes OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24592)
-- Name: docentes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.docentes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.docentes_id_seq OWNER TO postgres;

--
-- TOC entry 4838 (class 0 OID 0)
-- Dependencies: 218
-- Name: docentes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.docentes_id_seq OWNED BY public.docentes.id;


--
-- TOC entry 4660 (class 2604 OID 24589)
-- Name: alumnos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alumnos ALTER COLUMN id SET DEFAULT nextval('public.alumnos_id_seq'::regclass);


--
-- TOC entry 4662 (class 2604 OID 24605)
-- Name: cursos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos ALTER COLUMN id SET DEFAULT nextval('public.cursos_id_seq'::regclass);


--
-- TOC entry 4664 (class 2604 OID 24662)
-- Name: cursos_alumnos id_curso_alumno; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos_alumnos ALTER COLUMN id_curso_alumno SET DEFAULT nextval('public.cursos_alumnos_id_curso_alumno_seq'::regclass);


--
-- TOC entry 4661 (class 2604 OID 24596)
-- Name: docentes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.docentes ALTER COLUMN id SET DEFAULT nextval('public.docentes_id_seq'::regclass);


--
-- TOC entry 4821 (class 0 OID 24586)
-- Dependencies: 217
-- Data for Name: alumnos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.alumnos VALUES (4, 'Alejandro', 'Zdut', 37331650, '1993-07-02', 30, '650-11-3');
INSERT INTO public.alumnos VALUES (5, 'Sofia', 'Gomes', 21244656, '1990-05-15', 33, '656-13-4');
INSERT INTO public.alumnos VALUES (7, 'Isabella', 'Fernandez', 74562301, '1988-03-22', 35, '301-13-6');
INSERT INTO public.alumnos VALUES (8, 'Benjamin', 'Lopez', 89123456, '1992-09-14', 31, '456-13-7');
INSERT INTO public.alumnos VALUES (9, 'Valentina', 'Diaz', 65432178, '1997-02-17', 26, '178-13-8');
INSERT INTO public.alumnos VALUES (11, 'Emma', 'Gonzalez', 12349876, '1994-04-05', 29, '876-13-10');
INSERT INTO public.alumnos VALUES (12, 'Leonardo', 'Sanchez', 45678901, '1991-08-28', 32, '901-13-11');
INSERT INTO public.alumnos VALUES (14, 'Lucas', 'Ramirez', 23456789, '1996-10-07', 27, '789-13-13');
INSERT INTO public.alumnos VALUES (15, 'Gabriel', 'Lopez', 98765432, '1993-06-18', 30, '432-13-14');
INSERT INTO public.alumnos VALUES (16, 'Julia', 'Gomez', 34561278, '1990-04-09', 33, '278-13-15');
INSERT INTO public.alumnos VALUES (17, 'Lucas', 'Fernandez', 56782341, '1995-01-25', 28, '341-13-16');
INSERT INTO public.alumnos VALUES (18, 'Camila', 'Diaz', 87651234, '1988-08-11', 35, '234-13-17');
INSERT INTO public.alumnos VALUES (19, 'Matias', 'Perez', 12347865, '1992-12-30', 31, '865-13-18');
INSERT INTO public.alumnos VALUES (20, 'Valeria', 'Gonzalez', 78901234, '1997-03-05', 26, '234-13-19');
INSERT INTO public.alumnos VALUES (21, 'Nicolas', 'Ramirez', 67890123, '1991-09-02', 32, '123-13-20');
INSERT INTO public.alumnos VALUES (22, 'Luna', 'Rodriguez', 89012345, '1989-11-19', 34, '345-13-21');
INSERT INTO public.alumnos VALUES (23, 'Facundo', 'Fernandez', 56789012, '1996-02-07', 27, '12-13-22');
INSERT INTO public.alumnos VALUES (25, 'Delfina', 'Gomez', 32109876, '1995-08-15', 28, '876-13-24');
INSERT INTO public.alumnos VALUES (27, 'Jazmin', 'Gonzalez', 98765431, '1992-07-20', 31, '431-13-26');
INSERT INTO public.alumnos VALUES (28, 'Thiago', 'Torres', 10987654, '1997-04-12', 26, '654-13-27');
INSERT INTO public.alumnos VALUES (29, 'Maia', 'Sanchez', 54321098, '1987-01-28', 36, '98-13-28');
INSERT INTO public.alumnos VALUES (31, 'Mauro', 'Lombardo', 36721142, '1996-06-24', 27, '142-15-30');
INSERT INTO public.alumnos VALUES (32, 'Santiago', 'Lattanzi', 21456214, '2004-05-26', 19, '214-16-31');
INSERT INTO public.alumnos VALUES (33, 'Alejo', 'Yammal', 42312775, '2001-04-25', 22, '775-23-32');
INSERT INTO public.alumnos VALUES (34, 'Tomas', 'Temporelli', 45488231, '2004-04-09', 19, '231-23-33');
INSERT INTO public.alumnos VALUES (35, 'Valentino', 'Mezzavilla', 42198221, '2001-11-05', 22, '221-26-34');
INSERT INTO public.alumnos VALUES (36, 'Juan Martin', 'Perez', 43221569, '2002-12-01', 21, '569-27-35');


--
-- TOC entry 4825 (class 0 OID 24602)
-- Dependencies: 221
-- Data for Name: cursos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cursos VALUES (172, 'Presencial', 'PARADIGMAS DE PROGRAMACION', 2, 'Se veran distintos patrones de diseño en Java y sus respectivas aplicaciones en situaciones reales', 'Realizar un TP integrador aplicando la mayor cantidad de patrones posibles', 'Estudiantes de segundo año de Ingenieria en Software', 34000, '18:30', '23:00', 'TUESDAY', 1, '');
INSERT INTO public.cursos VALUES (166, 'Virtual', 'CURSO DE PROGRAMACION JAVA', 12345, 'Aprende los fundamentos de la programación en Java', 'Desarrollar habilidades en programación orientada a objetos', 'Principiantes en programación', 99.99, '18:30', '20:30', 'WEDNESDAY', 2, 'https://meet.example.com/javacourse');
INSERT INTO public.cursos VALUES (173, 'Presencial', 'TALLER DE ANALISIS Y DISENO DE SOFTWARE', 1, 'En este curso se vera UML y la aplicacion de los diagramas a un Plan Unificado de Desarrollo', 'Realizar un trabajo practico, simulando un PUD, aplicando los conceptos estudiados ', 'Estudiantes de segundo y tercer año de Ingenieria en Software', 12560, '18:30', '23:00', 'THURSDAY', NULL, '');
INSERT INTO public.cursos VALUES (174, 'Presencial', 'PARADIGMAS DE PROGRAMACION', 3, 'Se veran distintos patrones de diseño en Java y sus respectivas aplicaciones en situaciones reales', 'Realizar un TP integrador aplicando la mayor cantidad de patrones posibles', 'Estudiantes de segundo año de Ingenieria en Software', 34000, '18:30', '23:00', 'TUESDAY', NULL, '');


--
-- TOC entry 4827 (class 0 OID 24659)
-- Dependencies: 223
-- Data for Name: cursos_alumnos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cursos_alumnos VALUES (89, 172, 35);
INSERT INTO public.cursos_alumnos VALUES (90, 166, 35);
INSERT INTO public.cursos_alumnos VALUES (91, 172, 34);
INSERT INTO public.cursos_alumnos VALUES (97, 172, 28);
INSERT INTO public.cursos_alumnos VALUES (98, 172, 5);
INSERT INTO public.cursos_alumnos VALUES (99, 172, 11);
INSERT INTO public.cursos_alumnos VALUES (100, 172, 12);
INSERT INTO public.cursos_alumnos VALUES (101, 172, 15);
INSERT INTO public.cursos_alumnos VALUES (102, 172, 16);
INSERT INTO public.cursos_alumnos VALUES (103, 172, 33);
INSERT INTO public.cursos_alumnos VALUES (104, 172, 31);
INSERT INTO public.cursos_alumnos VALUES (105, 172, 27);
INSERT INTO public.cursos_alumnos VALUES (106, 172, 25);
INSERT INTO public.cursos_alumnos VALUES (107, 172, 23);
INSERT INTO public.cursos_alumnos VALUES (108, 172, 22);
INSERT INTO public.cursos_alumnos VALUES (109, 174, 21);
INSERT INTO public.cursos_alumnos VALUES (38, 166, 4);
INSERT INTO public.cursos_alumnos VALUES (39, 166, 5);
INSERT INTO public.cursos_alumnos VALUES (41, 166, 7);
INSERT INTO public.cursos_alumnos VALUES (42, 166, 8);
INSERT INTO public.cursos_alumnos VALUES (43, 166, 9);
INSERT INTO public.cursos_alumnos VALUES (45, 166, 11);
INSERT INTO public.cursos_alumnos VALUES (46, 166, 12);
INSERT INTO public.cursos_alumnos VALUES (48, 166, 14);
INSERT INTO public.cursos_alumnos VALUES (49, 166, 15);
INSERT INTO public.cursos_alumnos VALUES (50, 166, 16);
INSERT INTO public.cursos_alumnos VALUES (51, 166, 17);
INSERT INTO public.cursos_alumnos VALUES (52, 166, 18);
INSERT INTO public.cursos_alumnos VALUES (53, 166, 19);
INSERT INTO public.cursos_alumnos VALUES (54, 166, 20);
INSERT INTO public.cursos_alumnos VALUES (55, 166, 21);
INSERT INTO public.cursos_alumnos VALUES (56, 166, 22);
INSERT INTO public.cursos_alumnos VALUES (57, 166, 23);
INSERT INTO public.cursos_alumnos VALUES (59, 166, 25);
INSERT INTO public.cursos_alumnos VALUES (61, 166, 27);
INSERT INTO public.cursos_alumnos VALUES (62, 166, 28);
INSERT INTO public.cursos_alumnos VALUES (66, 166, 31);
INSERT INTO public.cursos_alumnos VALUES (84, 166, 34);
INSERT INTO public.cursos_alumnos VALUES (88, 172, 32);


--
-- TOC entry 4823 (class 0 OID 24593)
-- Dependencies: 219
-- Data for Name: docentes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.docentes VALUES (1, 'Pablo', 'Virgolini', 26004744, '1972-08-21', 51, '-------Curriculum Vitae-------
Nivel de educacion completo: Masters
Descripcion:Seasoned cybersecurity expert with a focus on network security and threat detection.
El rubro en el que me especializo es: Cybersecurity');
INSERT INTO public.docentes VALUES (2, 'Leonardo', 'Requena', 35021883, '1987-04-19', 36, '-------Curriculum Vitae-------
Nivel de educacion completo: Bachelor''s Degree in Software Engineering
Descripcion:Enthusiastic software engineer with experience in full-stack development and agile methodologies.
El rubro en el que me especializo es: Software Engineering, Full-Stack Development');


--
-- TOC entry 4839 (class 0 OID 0)
-- Dependencies: 216
-- Name: alumnos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alumnos_id_seq', 36, true);


--
-- TOC entry 4840 (class 0 OID 0)
-- Dependencies: 222
-- Name: cursos_alumnos_id_curso_alumno_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cursos_alumnos_id_curso_alumno_seq', 109, true);


--
-- TOC entry 4841 (class 0 OID 0)
-- Dependencies: 220
-- Name: cursos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cursos_id_seq', 175, true);


--
-- TOC entry 4842 (class 0 OID 0)
-- Dependencies: 218
-- Name: docentes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.docentes_id_seq', 10, true);


--
-- TOC entry 4666 (class 2606 OID 24591)
-- Name: alumnos alumnos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT alumnos_pkey PRIMARY KEY (id);


--
-- TOC entry 4672 (class 2606 OID 24664)
-- Name: cursos_alumnos cursos_alumnos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos_alumnos
    ADD CONSTRAINT cursos_alumnos_pkey PRIMARY KEY (id_curso_alumno);


--
-- TOC entry 4670 (class 2606 OID 24609)
-- Name: cursos cursos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT cursos_pkey PRIMARY KEY (id);


--
-- TOC entry 4668 (class 2606 OID 24600)
-- Name: docentes docentes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.docentes
    ADD CONSTRAINT docentes_pkey PRIMARY KEY (id);


--
-- TOC entry 4676 (class 2620 OID 24749)
-- Name: cursos before_insert_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER before_insert_trigger BEFORE INSERT ON public.cursos FOR EACH ROW EXECUTE FUNCTION public.remove_accents_before_insert();


--
-- TOC entry 4674 (class 2606 OID 24724)
-- Name: cursos_alumnos fk_id_alumno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos_alumnos
    ADD CONSTRAINT fk_id_alumno FOREIGN KEY (id_alumno) REFERENCES public.alumnos(id) ON DELETE CASCADE;


--
-- TOC entry 4675 (class 2606 OID 24729)
-- Name: cursos_alumnos fk_id_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos_alumnos
    ADD CONSTRAINT fk_id_curso FOREIGN KEY (id_curso) REFERENCES public.cursos(id) ON DELETE CASCADE;


--
-- TOC entry 4673 (class 2606 OID 24736)
-- Name: cursos fk_id_docente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT fk_id_docente FOREIGN KEY (id_docente) REFERENCES public.docentes(id) ON DELETE CASCADE;


-- Completed on 2023-11-28 10:48:22

--
-- PostgreSQL database dump complete
--

