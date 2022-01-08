<?php

namespace App\Models;

use CodeIgniter\Model;

class listDodolModel extends Model{

    protected $table = 'listdodol';
    protected $primaryKey = 'ID';

    protected $allowedFields = [
        'Nama_Dodol',
        'Harga',
        'Deskripsi'
    ];
    protected $validationRules = [
        'Nama_Dodol' => 'required',
        'Harga' => 'required',
        'Deskripsi' => 'required',
    ];
    protected $validationMessages = [
        'Nama_Dodol' => [
            'required' => 'Nama Dodol Tidak Boleh Kosong'
        ],
        'Harga' => [
            'required' => 'Harga Tidak Boleh Kosong'
        ],
        'Deskripsi' => [
            'required' => 'Deskripsi Tidak Boleh Kosong'
        ],
    ];


}