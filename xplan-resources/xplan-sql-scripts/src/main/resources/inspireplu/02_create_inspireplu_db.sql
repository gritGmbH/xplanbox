-- as user postgres
CREATE DATABASE inspireplu
  WITH ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE inspireplu
  IS 'Planned Land Use - canonical schema';