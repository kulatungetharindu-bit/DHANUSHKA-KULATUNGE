SELECT *
FROM EMP_Data
WHERE EMP_Department IN (
    SELECT EMP_Department
    FROM EMP_Data
    GROUP BY EMP_Department
    HAVING COUNT(*) > 1
);
