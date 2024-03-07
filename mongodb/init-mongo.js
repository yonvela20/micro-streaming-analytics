db.createUser({
	user: 'admin',
	pwd: 'admin',
	roles: ['dbAdmin', 'readWrite'],
});