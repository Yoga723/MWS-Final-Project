<?php

namespace App\Models;

use CodeIgniter\Model;

class listBurgerModel extends Model{

    protected $table = 'listburger';
    protected $primaryKey = 'ID';

    protected $allowedFields = [
        'Nama_Burger',
        'Harga',
        'Deskripsi'
    ];
    protected $validationRules = [
        'Nama_Burger' => 'required',
        'Harga' => 'required',
        'Deskripsi' => 'required',
    ];
    protected $validationMessages = [
        'Nama_Burger' => [
            'required' => 'Nama Burger Tidak Boleh Kosong'
        ],
        'Harga' => [
            'required' => 'Harga Tidak Boleh Kosong'
        ],
        'Deskripsi' => [
            'required' => 'Deskripsi Tidak Boleh Kosong'
        ],
    ];


}