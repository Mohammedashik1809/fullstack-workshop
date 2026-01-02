DELIMITER $$

CREATE FUNCTION GetProjectStatus(project_id INT)
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
    DECLARE s_date DATE;
    DECLARE e_date DATE;

   
    SELECT start_date, end_date
    INTO s_date, e_date
    FROM projects
    WHERE id = project_id;


    IF s_date IS NULL AND e_date IS NULL THEN
        RETURN 'Unknown';
    END IF;

    IF s_date > CURDATE() THEN
        RETURN 'Not Started';
    ELSEIF CURDATE() BETWEEN s_date AND e_date THEN
        RETURN 'In Progress';

   
    ELSEIF e_date < CURDATE() THEN
        RETURN 'Completed';
    END IF;

    RETURN 'Unknown';
END$$

DELIMITER ;

SELECT
    name,
    start_date,
    end_date,
    GetProjectStatus(id) AS status
FROM projects;

