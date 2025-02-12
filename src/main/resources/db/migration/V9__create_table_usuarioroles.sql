CREATE TABLE usuario_roles (
    usuario_id UUID NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (usuario_id, role),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);