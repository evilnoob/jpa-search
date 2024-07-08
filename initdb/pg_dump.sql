--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3

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
-- Name: auth; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS auth;


ALTER SCHEMA auth OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: department; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE IF NOT EXISTS auth.department (
    id bigint NOT NULL,
    ref_id character varying,
    title character varying
);


ALTER TABLE auth.department OWNER TO postgres;

--
-- Name: department_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS auth.department_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE auth.department_id_seq OWNER TO postgres;

--
-- Name: department_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.department_id_seq OWNED BY auth.department.id;


--
-- Name: role; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE IF NOT EXISTS auth.role (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE auth.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS auth.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE auth.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.role_id_seq OWNED BY auth.role.id;


--
-- Name: user; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE IF NOT EXISTS auth."user" (
    id bigint NOT NULL,
    name character varying,
    department_id bigint NOT NULL,
    parent_id bigint
);


ALTER TABLE auth."user" OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS auth.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE auth.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.user_id_seq OWNED BY auth."user".id;


--
-- Name: user_login; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE IF NOT EXISTS auth.user_login (
    id bigint NOT NULL,
    login character varying NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE auth.user_login OWNER TO postgres;

--
-- Name: user_login_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE IF NOT EXISTS auth.user_login_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE auth.user_login_id_seq OWNER TO postgres;

--
-- Name: user_login_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.user_login_id_seq OWNED BY auth.user_login.id;


--
-- Name: user_role; Type: TABLE; Schema: auth; Owner: postgres
--

CREATE TABLE IF NOT EXISTS auth.user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE auth.user_role OWNER TO postgres;

--
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: auth; Owner: postgres
--

CREATE SEQUENCE auth.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE auth.user_role_id_seq OWNER TO postgres;

--
-- Name: user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: auth; Owner: postgres
--

ALTER SEQUENCE auth.user_role_id_seq OWNED BY auth.user_role.id;


--
-- Name: department id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.department ALTER COLUMN id SET DEFAULT nextval('auth.department_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.role ALTER COLUMN id SET DEFAULT nextval('auth.role_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."user" ALTER COLUMN id SET DEFAULT nextval('auth.user_id_seq'::regclass);


--
-- Name: user_login id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_login ALTER COLUMN id SET DEFAULT nextval('auth.user_login_id_seq'::regclass);


--
-- Name: user_role id; Type: DEFAULT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_role ALTER COLUMN id SET DEFAULT nextval('auth.user_role_id_seq'::regclass);


--
-- Data for Name: department; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth.department (id, ref_id, title) FROM stdin;
1	MAIN	Основной
2	ADD_1	Дополнительный 1
3	ADD_2	Дополнительный 1
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth.role (id, name) FROM stdin;
1	ADMIN
2	USER
3	OWNER
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth."user" (id, name, department_id, parent_id) FROM stdin;
1	Alex	1	\N
4	John	2	\N
5	Kate	3	5
3	Bob	2	1
2	Tom	1	1
\.


--
-- Data for Name: user_login; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth.user_login (id, login, user_id) FROM stdin;
1	admin_alex	1
2	owner_alex	1
3	admin_tom	2
4	admin_bob	3
5	user_john	4
6	user_kate	5
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: auth; Owner: postgres
--

COPY auth.user_role (id, user_id, role_id) FROM stdin;
1	1	3
2	1	1
3	2	1
4	3	1
5	4	2
6	5	2
\.


--
-- Name: department_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.department_id_seq', 3, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.role_id_seq', 3, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.user_id_seq', 5, true);


--
-- Name: user_login_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.user_login_id_seq', 6, true);


--
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: auth; Owner: postgres
--

SELECT pg_catalog.setval('auth.user_role_id_seq', 6, true);


--
-- Name: department pk_department; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.department
    ADD CONSTRAINT pk_department PRIMARY KEY (id);


--
-- Name: role pk_role; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.role
    ADD CONSTRAINT pk_role PRIMARY KEY (id);


--
-- Name: user pk_user; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth."user"
    ADD CONSTRAINT pk_user PRIMARY KEY (id);


--
-- Name: user_login pk_user_login; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_login
    ADD CONSTRAINT pk_user_login PRIMARY KEY (id);


--
-- Name: user_role pk_user_role; Type: CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_role
    ADD CONSTRAINT pk_user_role PRIMARY KEY (id);


--
-- Name: user_login fk_user_login_user_id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_login
    ADD CONSTRAINT fk_user_login_user_id FOREIGN KEY (user_id) REFERENCES auth."user"(id);


--
-- Name: user_role fk_user_role_role_id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_role
    ADD CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES auth.role(id);


--
-- Name: user_role fk_user_role_user_id; Type: FK CONSTRAINT; Schema: auth; Owner: postgres
--

ALTER TABLE ONLY auth.user_role
    ADD CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES auth."user"(id);


--
-- PostgreSQL database dump complete
--

