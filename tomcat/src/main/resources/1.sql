SELECT
	*
FROM
	`test_graph` `g`
LEFT JOIN `test_node` `n` ON ((`g`.`id` = `n`.`g_id`))
LEFT JOIN `test_api` `a` ON ((`a`.`n_id` = `n`.`id`))
WHERE
	a.success = 'true' AND a.seq = 0