<?php

namespace App\Models;

use CodeIgniter\Model;

class listPizzaModel extends Model{

    protected $table = 'listpizza';
    protected $primaryKey = 'ID';

    protected $allowedFields = [
        'Nama_Pizza',
        'Harga',
        'Deskripsi'
    ];
    protected $validationRules = [
        'Nama_Pizza' => 'required',
        'Harga' => 'required',
        'Deskripsi' => 'required',
    ];
    protected $validationMessages = [
        'Nama_Pizza' => [
            'required' => 'Nama Pizza Tidak Boleh Kosong'
        ],
        'Harga' => [
            'required' => 'Harga Tidak Boleh Kosong'
        ],
        'Deskripsi' => [
            'required' => 'Deskripsi Tidak Boleh Kosong'
        ],
    ];


}