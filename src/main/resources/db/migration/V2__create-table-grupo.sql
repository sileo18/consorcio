CREATE TABLE grupo (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(4) NOT NULL,
    admnistracaoPorcentagem INT NOT NULL,
    duracaoMeses INT NOT NULL
);