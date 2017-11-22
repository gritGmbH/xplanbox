-- as user postgres
CREATE DATABASE plu_canonical
  WITH OWNER = deegree
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE plu_canonical
  IS 'Planned Land Use - canonical schema';

GRANT CONNECT ON DATABASE plu_canonical TO deegree;