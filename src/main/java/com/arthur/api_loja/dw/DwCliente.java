package com.arthur.api_loja.dw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dw_clientes")
@Getter
@Setter
public class DwCliente {

    @Id
    private Long usuarioId;

    private String email;
    private String role;
    private Boolean ativo;
}
