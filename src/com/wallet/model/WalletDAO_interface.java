package com.wallet.model;

import java.util.List;

public interface WalletDAO_interface {
    public void insert(WalletVO walletVO);
    public List<WalletVO> getAll();
    public List<WalletVO> getByMemno(String mem_no);
}
