insert into authorities values
    (1, "User_role"), (2, "Admin_role");
insert into users values
    (3, "ad.akantev@phystech.edu", false, "Alexandr",	"Vasiliy", "$2a$10$VYM/W6nypxQd6lyXvs17Ee5eoiWeh.vuL.gX3alQr3A4rqwJF78nG",	"+79000000000");
insert into users_authorities values
    (3, 2);