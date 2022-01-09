<?php

namespace App\Models;

use CodeIgniter\Model;

class listSnackModel extends Model{

    protected $table = 'listsnack';
    protected $primaryKey = 'ID';

    protected $allowedFields = [
        'Nama_Snack',
        'Harga',
        'Deskripsi'
    ];
    protected $validationRules = [
        'Nama_Snack' => 'required',
        'Harga' => 'required',
        'Deskripsi' => 'required',
    ];
    protected $validationMessages = [
        'Nama_Snack' => [
            'required' => 'Nama Snack Tidak Boleh Kosong'
        ],
        'Harga' => [
            'required' => 'Harga Tidak Boleh Kosong'
        ],
        'Deskripsi' => [
            'required' => 'Deskripsi Tidak Boleh Kosong'
        ],
    ];


}