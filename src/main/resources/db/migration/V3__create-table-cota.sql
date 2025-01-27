CREATE TABLE cota (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(4) NOT NULL,
    categoria DOUBLE PRECISION NOT NULL,
    credito INT NOT NULL,
    planoMeses INT NOT NULL,
    totalPago INT NOT NULL,
    parcela DOUBLE PRECISION NOT NULL,
    usuario_id UUID REFERENCES usuario(id),
    grupo_id UUID REFERENCES grupo(id)
);