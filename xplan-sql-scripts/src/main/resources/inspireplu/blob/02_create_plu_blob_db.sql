-- as user postgres
CREATE DATABASE plu_blob
  WITH OWNER = deegree
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE plu_blob
  IS 'Planned Land Use - blob schema';

GRANT CONNECT ON DATABASE plu_blob TO deegree;