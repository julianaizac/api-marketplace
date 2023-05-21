INSERT INTO TB_CATEGORY (id, name, description, creation_date) VALUES
    ('0f8fad5b-d9cb-469f-a165-70867728950e', 'Eletrônicos', 'Categoria de produtos eletrônicos', '2023-05-14T09:00:00'),
    ('7c9e6679-7425-40de-944b-e07fc1f90ae7', 'Roupas', 'Categoria de roupas', '2023-05-14T10:00:00');

INSERT INTO TB_PRODUCT (id, name, description, price, creation_date, category_id) VALUES
    ('fe1a8a84-62f8-43a6-a0ce-058aabe0e8f2', 'Smartphone', 'Um smartphone avançado', '5500.00', '2023-05-14T09:30:00', '0f8fad5b-d9cb-469f-a165-70867728950e'),
    ('d4f8dc54-4a4f-4a48-a4e1-65e9761ab351', 'Notebook', 'Um notebook potente', '3000.00', '2023-05-14T10:30:00', '0f8fad5b-d9cb-469f-a165-70867728950e'),
    ('0e345ae9-098b-45d7-8c7f-031dd33ad5ad', 'Camiseta', 'Uma camiseta confortável', '50.00', '2023-05-14T11:00:00', '7c9e6679-7425-40de-944b-e07fc1f90ae7');

INSERT INTO TB_STOCK (id, creation_date, amount, product_id) VALUES
    ('c8a7f9f4-62c7-45ae-8a41-91aeed3e1f8d', '2023-05-14T09:30:00', 10, 'fe1a8a84-62f8-43a6-a0ce-058aabe0e8f2'),
    ('ac9e95b7-eb26-4b27-8b2d-4d72e227c4e7', '2023-05-14T10:30:00', 5, 'd4f8dc54-4a4f-4a48-a4e1-65e9761ab351'),
    ('352e16f7-1fe7-496f-bc6e-ec2ce7c97e12', '2023-05-14T11:00:00', 20, '0e345ae9-098b-45d7-8c7f-031dd33ad5ad');
